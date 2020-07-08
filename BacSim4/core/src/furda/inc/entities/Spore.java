package furda.inc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import furda.inc.world.GameMap;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spore extends Entity {

    Texture image;

    private int lastD1, lastD2;

    private static final int SPEED = 80;

    public Spore(float x, float y, GameMap map) {
        super(x, y, EntityType.SPORE, map);
        image = new Texture("Spore1.png");
        lastD1 = 0;
        lastD2 = 0;
    }

    @Override
    public void update(float deltaTime, float gravity) {
        int Direction;
        
        Direction = ThreadLocalRandom.current().nextInt(1, 5);
        //Here we use negative speed as we are moving char to the left
        if(Direction == 1)
            moveX(-SPEED * deltaTime);

        if(Direction == 2)
            moveX(SPEED * deltaTime);

        if(Direction == 3)
            moveY(SPEED * deltaTime);

        if(Direction == 4)
            moveY(-SPEED * deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());

    }




}
