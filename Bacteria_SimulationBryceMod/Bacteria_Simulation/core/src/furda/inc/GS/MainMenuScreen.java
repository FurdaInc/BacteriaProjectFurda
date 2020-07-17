package furda.inc.GS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import furda.inc.Simulation;

public class MainMenuScreen implements Screen{
    private static  final int EXIT_BUTTON_WIDTH = 80;
    private static  final int EXIT_BUTTON_X = Simulation.WIDTH / 2;
    private static  final int EXIT_BUTTON_HEIGHT = 80;
    private static  final int EXIT_BUTTON_Y = 420;
    private static  final int PLAY_BUTTON_Y = 560;
    private static  final int PLAY_BUTTON_WIDTH = 80;
    private static  final int PLAY_BUTTON_HEIGHT = 80;

    Simulation game;
    Texture img;
    float x;
    float y;



    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    public MainMenuScreen (Simulation game){
        this.game = game;
        playButtonActive = new Texture("playActive.png");
        playButtonInactive = new Texture("playInactive.png");
        exitButtonActive = new Texture("exitActive.png");
        exitButtonInactive = new Texture("exitInactive.png");
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.6f, 0.4f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        int x = 400 - 40;
        if(Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && Simulation.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + 80 && Simulation.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, x, 100,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, x, 100,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && Simulation.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + 80 && Simulation.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive, x, 250,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, x, 250,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

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
