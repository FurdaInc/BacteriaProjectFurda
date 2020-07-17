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

    int[] xSpawn = new int[32];
    int[] ySpawn = new int[32];

    Entity toRemove;
    bacteriaBlue blueToAlter;
    boolean blueBind=false;
    bacteriaRed redToAlter;
    boolean redBind=false;
    boolean blueSporeBindRed = false;
    Garlic tempGar;


    static int spawnCountAll = 0;
    static int spawnCountB = 0;
    static int spawnCountR = 0;

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("BacMap4.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        spawnWidthL = 16;
        spawnWidthR = (getWidth()-6) * 16;

        spawnHeightL = 16;
        spawnHeightR = (getHeight()-6) * 16;
        entities.add(new SpawnButton(0,0,this));

        xSpawn[0] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
        ySpawn[0] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);



        //xSpawn[0] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
        //ySpawn[0] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
        //entities.add(new Player(xSpawn[0],ySpawn[0],this));
        //entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));
        entities.add(new BlueSpore(200,200,this));




        populateSpawns();

        /*xSpawn[1] = ThreadLocalRandom.current().nextInt(2, getWidth()-2);
        for (int i =0; i<xSpawn.length; i++){
            while (xSpawn[1] > xSpawn[i]-32 && xSpawn[1] < xSpawn[i] +32 )
                xSpawn[1] = ThreadLocalRandom.current().nextInt(2, getWidth()-2);
        }
        ySpawn[1] = ThreadLocalRandom.current().nextInt(2, getHeight()-2);
        for (int i =0; i<ySpawn.length; i++){
            while (ySpawn[1] > ySpawn[i]-32 && ySpawn[1] < ySpawn[i] +32 )
                ySpawn[1] = ThreadLocalRandom.current().nextInt(2, getHeight()-2);
        }
        for (int k = 1; k<8; k++) {
            entities.add(new Player(xSpawn[k], ySpawn[k], this));
        }*/


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

        if(updateBlue)
            addBlue();
        if(updateRed)
            addRed();
        entityIterator = entities.iterator();
        for(Entity entity : entities){
            //entity.update(delta, -9.8f);
            entity.update(delta, 0);
            for(Entity entity2 : entities){
                if(entity.getType() == entity2.getType()){
                    continue;
                }else{
                    if(this.doObjectsCollide(entity, entity2)){
                        if(entity.getType() == EntityType.BLUESPORE){
                            if(entity2.getType() == EntityType.BACTERIABLUE){
                                toRemove = entity;
                                blueToAlter = (bacteriaBlue) entity2;
                                blueBind = true;
                            }
                            if(entity2.getType() == EntityType.BACTERIARED){
                                toRemove = entity;
                                redToAlter = (bacteriaRed) entity2;
                                blueSporeBindRed = true;

                            }

                        }
                    }
                }
            }
        }


        if(blueBind==true) {
            if(blueToAlter.getSporeCount() < 4){
                blueToAlter.changeForm();
                entities.remove(toRemove);
                blueToAlter = null;
            }
            blueToAlter = null;
            toRemove = null;
            blueBind = false;

        }

        if(blueSporeBindRed){
            //do stuff here probably
            redToAlter = null;
            toRemove = null;
            blueSporeBindRed = false;
        }





    }

    @Override
    public void dispose() {
        tiledMap.dispose();

    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {

        Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);

        if(cell!=null){
            TiledMapTile tile = cell.getTile();
            if (tile!=null){
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

    public void populateSpawns(){
        for (int j = 1; j<30; j++){
            checkSpace = false;
            xSpawn[j] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
            ySpawn[j] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
            while(checkSpace == false) {
                checkSpace = true;
                for (int i = 0; i < j; i++) {
                    while ((xSpawn[j] > xSpawn[i] - 56 && xSpawn[j] < xSpawn[i] + 56) && (ySpawn[j] > ySpawn[i] - 64 && ySpawn[j] < ySpawn[i] + 64)) {
                        checkSpace = false;
                        xSpawn[j] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
                        ySpawn[j] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
                    }
                }
            }
        }
    }

    public void addBlue(){
        if(spawnCountB>=30){
            return;
        }
        entities.add(new bacteriaBlue(xSpawn[spawnCountAll],ySpawn[spawnCountAll],this));
        spawnCountB++;
        spawnCountAll++;
        updateBlue = false;
    }

    public void addRed(){
        if(spawnCountR>=4){
            return;
        }
        entities.add(new bacteriaRed(xSpawn[spawnCountAll],ySpawn[spawnCountAll],this));
        spawnCountR++;
        spawnCountAll++;
        updateRed = false;
    }

    public void setUpdateBlue(){
        updateBlue = true;
    }

    public void setUpdateRed(){
        updateRed = true;
    }



}
