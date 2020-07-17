package furda.inc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import furda.inc.world.GameMap;
import furda.inc.world.TileType;
import furda.inc.world.TiledGameMap;

public class simBase extends ApplicationAdapter {
    //ApplicationAdapter is like an interface, create, render and dispose are all methods of application adapter

	SpriteBatch batch;


    OrthographicCamera cam;
	GameMap gameMap;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();


		cam = new OrthographicCamera();
		cam.setToOrtho(false, w, h);
		cam.update();

		gameMap = new TiledGameMap();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(cam, batch);




		//The cod below is how I was moving the map around, as well as displaying what tile was being clicked
		if(Gdx.input.isTouched()){
			cam.translate(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}

		/*if(Gdx.input.justTouched()){
			//Here 'pos' is the location of where was clicked in the game world
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);

			if(type!=null){
				System.out.println("You clicked on tile with id " + type.getId() + " " + type.getName() + " " + type.isCollidable() + " " + type.getDamage());
			}

		}*/





	}
	
	@Override
	public void dispose () {

		batch.dispose();
		gameMap.dispose();

	}



}
