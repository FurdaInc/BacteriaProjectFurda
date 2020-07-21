package furda.inc.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import furda.inc.world.GameMap;

import java.util.concurrent.ThreadLocalRandom;

public class BlueSpore extends Entity {

    Texture image;

    private int lastD1, lastD2;
    private int Direction;

    bacteriaBlue parent;


    private static final int SPEED = 80;

    public BlueSpore(float x, float y, GameMap map, bacteriaBlue parent) {
        super(x, y, EntityType.BLUESPORE, map);
        image = new Texture("BlueSpore.png");
        lastD1 = 0;
        lastD2 = 0;
        this.parent = parent;
    }

    @Override
    public void update(float deltaTime, float gravity) {


        Direction = ThreadLocalRandom.current().nextInt(1, 5);
        //Here we use negative speed as we are moving char to the left

        while (Direction == lastD1 || Direction == lastD2)
            Direction = ThreadLocalRandom.current().nextInt(1, 5);

        //1 is down
        if(Direction == 1) {
            moveY(-SPEED * deltaTime);
            lastD1 = 3;
        }

        //2 is right
        if(Direction == 2) {
            moveX(SPEED * deltaTime);
            lastD2 = 4;
        }

        //3 is up
        if(Direction == 3) {
            moveY(SPEED * deltaTime);
            lastD1 = 1;
        }

        //4 is left
        if(Direction == 4) {
            moveX(-SPEED * deltaTime);
            lastD2 = 2;
        }

        if (map.doesRectCollideWithMap((this.pos.x + (SPEED * deltaTime)), this.pos.y, 32,32) || map.doesRectCollideWithMap((this.pos.x + (-SPEED * deltaTime)), this.pos.y, 32,32) || map.doesRectCollideWithMap(this.pos.x, (this.pos.y + (SPEED * deltaTime)), 32,32) || map.doesRectCollideWithMap(this.pos.x, (this.pos.y + (-SPEED * deltaTime)), 32,32)){
            lastD1 = 0;
            lastD2 = 0;
        }

    }

    @Override
    public void playAnimation() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }

    public boolean returnParent(bacteriaBlue bac){
        if (this.parent == bac) {
            return true;
        }
        return false;
    }
}