package furda.inc.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import furda.inc.world.GameMap;

public abstract class Entity {

    protected Vector2 pos; // keeps track of x/y coordinates
    protected EntityType type;
    protected float velocityY = 0; // This is for things like jumping
    protected GameMap map;
    protected boolean grounded = false;
    protected boolean isBlue;

    public Entity(float x, float y, EntityType type, GameMap map) {
        this.pos = new Vector2(x,y);
        this.type = type;
        this.map = map;
    }

    public void update(float deltaTime, float gravity){
        float newY = pos.y;
        this.velocityY += gravity * deltaTime * getweight();
        newY += this.velocityY * deltaTime;

        if(map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())){
            //If velocity is less than 0 player must be falling down
            //Means we can reset grounded to be true (player is either grounded or about to be)
            if(velocityY < 0){
                this.pos.y = (float) Math.floor(pos.y);
                grounded = true;
            }
            this.velocityY = 0;
        }else{
            this.pos.y = newY;
            grounded = false;
        }
    }

    public abstract void render (SpriteBatch batch);

    protected void moveX(float amount){
        float newX = this.pos.x + amount;
        //Below checks if they can move where they are trying to move.
        if(!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight()))
            this.pos.x = newX;
    }

    protected void moveY(float amount){
        float newY = this.pos.y + amount;
        //Below checks if they can move where they are trying to move.
        if(!map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight()))
            this.pos.y = newY;
    }

    public Vector2 getPos() {
        return pos;
    }

    public EntityType getType() {
        return type;
    }

    public float getX(){
        return pos.x;
    }

    public float getY(){
        return pos.y;
    }

    public boolean isGrounded() {
        return grounded;
    }



    public int getWidth(){
        return type.getWidth();
    }

    public int getHeight(){
        return type.getHeight();
    }

    public float getweight(){
        return type.getWeight();
    }


}
