package furda.inc.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import furda.inc.world.GameMap;

import java.util.concurrent.ThreadLocalRandom;

public class RedSpore extends Entity {

    Texture image;

    private int lastD1, lastD2;
    private int Direction;


    private static final int SPEED = 80;

    public RedSpore(float x, float y, GameMap map) {
        super(x, y, EntityType.REDSPORE, map);
        image = new Texture("Spore1.png");
        lastD1 = 0;
        lastD2 = 0;
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

        if (map.doesRectCollideWithMap((this.pos.x + (SPEED * deltaTime)), this.pos.y, 16,16) || map.doesRectCollideWithMap((this.pos.x + (-SPEED * deltaTime)), this.pos.y, 16,16) || map.doesRectCollideWithMap(this.pos.x, (this.pos.y + (SPEED * deltaTime)), 16,16) || map.doesRectCollideWithMap(this.pos.x, (this.pos.y + (-SPEED * deltaTime)), 16,16)){
            lastD1 = 0;
            lastD2 = 0;
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }




}
