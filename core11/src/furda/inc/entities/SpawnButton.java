package furda.inc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import furda.inc.world.GameMap;
import furda.inc.world.TiledGameMap;

import java.util.concurrent.locks.ReentrantLock;

public class SpawnButton extends Entity {

    Texture image;


    public SpawnButton(float x, float y, GameMap map) {
        super(x, y, EntityType.SPAWNBUTTON, map);
        image = new Texture("Karsa.jpg");


    }

    @Override
    public void update(float deltaTime, float gravity) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            map.setUpdateBlue();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)){
            map.setUpdateRed();

        }




    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }




}
