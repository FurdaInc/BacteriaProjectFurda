package furda.inc.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import furda.inc.entities.Player;
import furda.inc.entities.Spore;

import java.util.concurrent.ThreadLocalRandom;


public class TiledGameMap extends GameMap {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    final int spawnWidthL;
    final int spawnWidthR;
    final int spawnHeightL;
    final int spawnHeightR;

    int[] xSpawn = new int[16];
    int[] ySpawn = new int[16];

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("BacMap4.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        spawnWidthL = 16;
        spawnWidthR = (getWidth()-6) * 16;

        spawnHeightL = 16;
        spawnHeightR = (getHeight()-6) * 16;



        xSpawn[0] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
        ySpawn[0] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
        entities.add(new Player(xSpawn[0],ySpawn[0],this));

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

        entities.add(new Player(xSpawn[1],ySpawn[1],this));*/
        entities.add(new Spore(200, 200, this));

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
        super.update(delta);
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
        for (int j = 1; j<10; j++){
            xSpawn[j] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
            for (int i =0; i<j; i++){
                while (xSpawn[j] > xSpawn[i]-16 && xSpawn[j] < xSpawn[i] +16 ) {
                    xSpawn[j] = ThreadLocalRandom.current().nextInt(spawnWidthL, spawnWidthR);
                }

            }
            ySpawn[j] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
            for (int i =0; i<j; i++){
                while (ySpawn[j] > ySpawn[i]-16 && ySpawn[j] < ySpawn[i] +16 ) {
                    ySpawn[j] = ThreadLocalRandom.current().nextInt(spawnHeightL, spawnHeightR);
                }
            }
        }
    }
}
