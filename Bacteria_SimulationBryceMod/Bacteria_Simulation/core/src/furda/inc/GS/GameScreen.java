package furda.inc.GS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import furda.inc.Simulation;



public class GameScreen implements Screen {
    public static Texture backgroundTexture;
    Texture img;
    public static final float SPEED = 120;
    public static  final float ANIMATION_SPEED = 0.5f;
    public static final int BACTERIA_WIDTH = 800;
    public static final int BACTERIA_HEIGHT = 800;

    Animation [] rolls;

    float x;
    float y;
    float stateTime;

    int roll;

    Simulation game;



    public GameScreen(Simulation game){
        this.game = game;
        roll = 1;
        rolls = new Animation[3];
        backgroundTexture = new Texture("Background.png");
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("Bacteria.png"),201,130);
        rolls[roll] =  new Animation(ANIMATION_SPEED, rollSpriteSheet[0]);
    }



    public void create(){

    }


    @Override
    public void show() {
        img = new Texture("badlogic.jpg");


    }


    @Override
    public void render(float delta) {
        stateTime += delta;
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            y = y + SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y = y - SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x = x - SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x = x + SPEED * Gdx.graphics.getDeltaTime();
        }
        game.batch.begin();
        game.batch.draw(backgroundTexture,0,0,800,480);
        game.batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime,true), x,y,200,200);
        game.batch.end();

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
