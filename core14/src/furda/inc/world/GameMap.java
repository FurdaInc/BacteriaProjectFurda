package furda.inc.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import furda.inc.entities.*;
//import furda.inc.entities.bacteriaBlue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GameMap {

    protected ArrayList<Entity> entities;
    protected Iterator<Entity> entityIterator;





    public GameMap() {
        entities = new ArrayList<Entity>();
        //xSpawn = ThreadLocalRandom.current().nextInt(32, getPixelWidth()-32);
        //ySpawn = ThreadLocalRandom.current().nextInt(32, getPixelHeight()-32);
        //entities.add(new Player(xSpawn,ySpawn,this));
        //entities.add(new Player(100,200,this));
        //entities.add(new Spore(200, 200, this));
        //entities.add(new bacteriaBlue(60, 300, this));

    }

    public void render (OrthographicCamera camera, SpriteBatch batch){
        //This goes through and renders each object in the list "entity"
        //For us this would be initially our bacteria
        for (Entity entity : entities){
            entity.render(batch);
        }
    }
    public abstract void update (float delta);//{
/*        for(Entity entity : entities){
            //entity.update(delta, -9.8f);
            entity.update(delta, 0);
        }*/

    //}
    public abstract void dispose ();

    /**
     * Gets a tile by pixel location within the game world at a specified layer
     * @param layer (z coordinate?)
     * @param x
     * @param y
     * @return
     */

    public TileType getTileTypeByLocation(int layer, float x, float y){
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE) , (int) (y / TileType.TILE_SIZE));
    }


    /**
     * Gets a tile at it's coordinate within the map at a specified layer
     * @param layer
     * @param col
     * @param row
     * @return
     */
    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);


    /**
     * Checks if an item is within the borders of the map
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public boolean doesRectCollideWithMap(float x, float y, int width, int height){
        if(x<0 || y<0 || x+width > getPixelWidth() || y+height > getPixelHeight())
            return true;
        //This is finding the bottom left corner of the object using integer division
        //Math.ceil ensures that the number always rounds up.
        //This locates the TOP RIGHT square that the object is touching
        for (int row = (int) (y/TileType.TILE_SIZE); row < Math.ceil((y + height)/ TileType.TILE_SIZE); row++){
            for (int col = (int) (x/TileType.TILE_SIZE); col < Math.ceil((x + width)/ TileType.TILE_SIZE); col++){
                for(int layer =0; layer < getLayers(); layer++){
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    //If the tile is NOT NULL (We are selecting a tile that exists) and it has been checked as collidable
                    //Maybe here we can check if the item collides with other objects?
                    if(type != null && type.isCollidable()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean doObjectsCollide(Entity A, Entity B){
        if(B.getType() == EntityType.BACTERIABLUE || B.getType() == EntityType.BACTERIARED){
            if((A.getX()-A.getWidth()) < B.getX() && B.getX() < (A.getX()+A.getWidth()) && (A.getY()-A.getHeight()) < B.getY() && B.getY() < (A.getY()+A.getHeight())){
                System.out.println("Bottom Left");
                B.setBindPoint(1);
            }
            else if((A.getX()-A.getWidth()) < B.getX()+64 && B.getX()+64 < (A.getX()+A.getWidth()) && (A.getY()-A.getHeight()) < B.getY() && B.getY() < (A.getY()+A.getHeight())){
                System.out.println("Bottom Right");
                B.setBindPoint(2);
            }
            else if((A.getX()-A.getWidth()) < B.getX() && B.getX() < (A.getX()+A.getWidth()) && (A.getY()-A.getHeight()) < B.getY()+64 && B.getY()+64 < (A.getY()+A.getHeight())){
                System.out.println("Top Left");
                B.setBindPoint(3);
            }
            else if((A.getX()-A.getWidth()) < B.getX()+64 && B.getX()+64 < (A.getX()+A.getWidth()) && (A.getY()-A.getHeight()) < B.getY()+64 && B.getY()+64 < (A.getY()+A.getHeight())){
                System.out.println("Top Right");
                B.setBindPoint(4);
            }
        }
        if ((A.getX()-A.getWidth()) < B.getX() && B.getX() < (A.getX()+A.getWidth()) && (A.getY()-A.getHeight()) < B.getY() && B.getY() < (A.getY()+A.getHeight())){
            return true;
        }
        return false;
    }



    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

    public abstract void addBlue();
    public abstract void setUpdateBlue();
    public abstract void addRed();
    public abstract void setUpdateRed();
    public abstract void addGarlic();
    public abstract void setUpdateGarlic();
    public abstract void addAntiBiotics();
    public abstract void setUpdateAntiBiotics();
    public abstract void addWBC();
    public abstract void setUpdateWBC();

    public int getPixelWidth(){
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public int getPixelHeight(){
        return this.getHeight() * TileType.TILE_SIZE;
    }


}
