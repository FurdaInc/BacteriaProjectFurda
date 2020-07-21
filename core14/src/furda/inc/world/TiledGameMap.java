package furda.inc.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import furda.inc.entities.*;

import java.util.concurrent.ThreadLocalRandom;


public class TiledGameMap extends GameMap {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    final int spawnWidthL;
    final int spawnWidthR;
    final int spawnHeightL;
    final int spawnHeightR;

    boolean checkSpace;
    boolean updateBlue = false;
    boolean updateRed = false;
    boolean updateGarlic = false;
    boolean updateAntiBiotics = false;
    boolean updateWBC = false;

    int[] xSpawn = new int[32];
    int[] ySpawn = new int[32];

    // A temp parent. Mostly assigned to spores.
    Entity toRemove;


    bacteriaBlue blueToAlter; //Temp for initial spawn and for when the Bacteria bind.
    bacteriaBlue blueToRemove; //Used when blue Bacteria interacts with WBC
    boolean blueBind = false; //Boolean denoting if a spore has collided with Bacteria
    BlueSpore tempBlueSpore; //Temp blue used when spore is binding (and for parent check)

    bacteriaRed redToAlter; //Temp for initial spawn and for when the Bacteria bind.
    bacteriaRed redToRemove; //Used when red Bacteria interacts with WBC
    boolean redBind = false; //Boolean denoting if a spore has collided with Bacteria
    RedSpore tempRedSpore; //Temp red used when spore is binding (and for parent check)

    boolean blueSporeBindRed = false; //Boolean for if a blue spore binds to a red bacteria

    Garlic tempGar;

    WBC temp1WBC; //Used if WBC collides with blue bacteria
    WBC temp2WBC; //Used if WBC collides with red bacteria


    boolean WBCBlue = false; //Boolean if WBC collides with blue bacteria
    boolean WBCRed = false; //Boolean if WBC collides with red bacteria


    static int spawnCountAll = 0;
    static int spawnCountB = 0;
    static int spawnCountR = 0;

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("BacMap5.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        spawnWidthL = 32;
        spawnWidthR = ((getWidth()-10) * 16);


        spawnHeightL = 32;
        spawnHeightR = ((getHeight()-10) * 16);
        System.out.println(getHeight());
        System.out.println(spawnWidthR);
        System.out.println(spawnHeightR);
        entities.add(new SpawnButton(0, 0, this));

        xSpawn[0] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
        ySpawn[0] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);

        //entities.add(new Player(xSpawn[0],ySpawn[0],this));
        //entities.add(new BlueSpore(200,200,this));


        populateSpawns();


    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();


        //The below makes a call to the parent classes method 'render' (GameMap)
        //This is where I'll be adding my code later
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.render(camera, batch);
        batch.end();
    }

    @Override
    public void update(float delta) {

        if (updateBlue)
            addBlue();
        if (updateRed)
            addRed();
        if (updateGarlic)
            addGarlic();
        if (updateAntiBiotics)
            addAntiBiotics();
        if (updateWBC)
            addWBC();


        entityIterator = entities.iterator();
        for (Entity entity : entities) {
            //entity.update(delta, -9.8f);
            entity.update(delta, 0);
            for (Entity entity2 : entities) {
                if (entity.getType() == entity2.getType()) {
                    continue;
                } else {
                    if (this.doObjectsCollide(entity, entity2)) {
                        if (entity.getType() == EntityType.BLUESPORE) {
                            if (entity2.getType() == EntityType.BACTERIABLUE) {
                                tempBlueSpore = (BlueSpore) entity;
                                blueToAlter = (bacteriaBlue) entity2;
                                if (tempBlueSpore.returnParent(blueToAlter)) {
                                    blueToAlter = null;
                                    tempBlueSpore = null;
                                    blueBind = false;
                                    continue;
                                }
                                toRemove = entity;
                                blueBind = true;
                            }
                            if (entity2.getType() == EntityType.BACTERIARED) {
                                toRemove = entity;
                                redToAlter = (bacteriaRed) entity2;
                                blueSporeBindRed = true;

                            }


                        }

                        if (entity.getType() == EntityType.REDSPORE) {
                            if (entity2.getType() == EntityType.BACTERIARED) {
                                tempRedSpore = (RedSpore) entity;
                                redToAlter = (bacteriaRed) entity2;
                                if (tempRedSpore.returnParent(redToAlter)) {
                                    redToAlter = null;
                                    tempRedSpore = null;
                                    redBind = false;
                                    continue;
                                }
                                toRemove = entity;
                                redBind = true;
                            }
                        }
                        if (entity.getType() == EntityType.WBC) {
                            if (entity2.getType() == EntityType.BACTERIABLUE) {
                                blueToRemove = (bacteriaBlue) entity2;
                                temp1WBC = (WBC) entity;
                                WBCBlue = true;
                            }
                            if (entity2.getType() == EntityType.BACTERIARED) {
                                redToRemove = (bacteriaRed) entity2;
                                temp2WBC = (WBC) entity;
                                WBCRed = true;
                            }
                        }
                    }
                }
            }
        }


        if (blueBind == true) {
            if (blueToAlter.getSporeCount() < 4) {
                blueToAlter.changeForm();
                entities.remove(toRemove);
                entities.add(new BlueSpore(blueToAlter.getX(), blueToAlter.getY(), this, blueToAlter));
                blueToAlter = null;
                toRemove = null;
            }
            blueToAlter = null;
            toRemove = null;
            blueBind = false;

        }

        if (blueSporeBindRed) {
            //do stuff here probably
            redToAlter = null;
            toRemove = null;
            blueSporeBindRed = false;
        }

        if (redBind == true) {
            if (redToAlter.getSporeCount() < 4) {
                redToAlter.changeForm();
                entities.remove(toRemove);
                entities.add(new RedSpore(redToAlter.getX(), redToAlter.getY(), this, redToAlter));
                redToAlter = null;
                toRemove = null;
            }
            redToAlter = null;
            toRemove = null;
            redBind = false;

        }

        if (WBCBlue) {
            entities.remove(temp1WBC);
            entities.remove(blueToRemove);
            temp1WBC = null;
            blueToRemove = null;
            WBCBlue = false;
        }

        if (WBCRed) {
            entities.remove(temp2WBC);
            entities.remove(redToRemove);
            temp2WBC = null;
            redToRemove = null;
            WBCRed = false;
        }
    }

    @Override
    public void dispose() {
        tiledMap.dispose();

    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {

        Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);

        if (cell != null) {
            TiledMapTile tile = cell.getTile();
            if (tile != null) {
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
            //If the cell is null we are either clicking outside the map or it does not exist on the layer we are exploring
        }

        return null;
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }

    //Creates an array of randomised spawn locations for the bacteria
    public void populateSpawns() {
        for (int j = 1; j < 30; j++) {
            checkSpace = false;
            xSpawn[j] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
            ySpawn[j] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
            while (checkSpace == false) {
                checkSpace = true;
                for (int i = 0; i < j; i++) {
                    while ((xSpawn[j] > xSpawn[i] - 64 && xSpawn[j] < xSpawn[i] + 64) && (ySpawn[j] > ySpawn[i] - 64 && ySpawn[j] < ySpawn[i] + 64)) {
                        checkSpace = false;
                        xSpawn[j] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
                        ySpawn[j] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
                    }
                }
            }
        }
    }

    public void addBlue() {
        if (spawnCountB >=30) {
            return;
        }
        blueToAlter = new bacteriaBlue(xSpawn[spawnCountAll], ySpawn[spawnCountAll], this);
        entities.add(blueToAlter);
        entities.add(new BlueSpore(xSpawn[spawnCountAll], ySpawn[spawnCountAll], this, blueToAlter));
        blueToAlter = null;
        spawnCountB++;
        spawnCountAll++;
        updateBlue = false;
    }

    public void addRed() {
        if (spawnCountR >= 4) {
            return;
        }
        redToAlter = new bacteriaRed(xSpawn[spawnCountAll], ySpawn[spawnCountAll], this);
        entities.add(redToAlter);
        entities.add(new RedSpore(xSpawn[spawnCountAll], ySpawn[spawnCountAll], this, redToAlter));
        redToAlter = null;
        spawnCountR++;
        spawnCountAll++;
        updateRed = false;
    }

    public void addGarlic() {
        entities.add(new Garlic(ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR), ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR), this));
        updateGarlic = false;
    }

    public void addAntiBiotics() {
        entities.add(new Antibiotic(ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR), ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR), this));
        updateAntiBiotics = false;
    }

    public void addWBC() {
        entities.add(new WBC(ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR), ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR), this));
        updateWBC = false;
    }

    public void setUpdateBlue() {
        updateBlue = true;
    }

    public void setUpdateRed() {
        updateRed = true;
    }

    public void setUpdateGarlic() {
        updateGarlic = true;
    }

    public void setUpdateAntiBiotics() {
        updateAntiBiotics = true;
    }

    public void setUpdateWBC() {
        updateWBC = true;
    }


}
