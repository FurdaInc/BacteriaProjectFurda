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
        image = new Texture("SpawnMenu.png");


    }

    @Override
    public void playAnimation() {

    }

    @Override
    public void update(float deltaTime, float gravity) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            map.setUpdateBlue();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)){
            map.setUpdateRed();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            map.setUpdateGarlic();

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            map.setUpdateAntiBiotics();

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
            map.setUpdateWBC();

        }




    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }




}
