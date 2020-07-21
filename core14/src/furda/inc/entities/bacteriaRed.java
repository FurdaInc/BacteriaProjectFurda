package furda.inc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import furda.inc.world.GameMap;


public class bacteriaRed extends Entity {
    @Override
    public void playAnimation() {

    }

    private static final int SPEED = 80;
    private static final int JUMP_VELOCITY = 1;


    public static  final float ANIMATION_SPEED = 0.5f;
    Animation[] rolls;
    int roll;
    double stateTime = Math.random();

    int boundSpores;


    Texture image;
    TextureRegion[] redSpriteRoll = new TextureRegion[5];


    public bacteriaRed(float x, float y, GameMap map) {

        super(x, y, EntityType.BACTERIARED, map);
        //Intializes the picture of the bactera
        roll = 1;
        rolls = new Animation[5];
        boundSpores = 0;

        //TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("RedBacteria.png"), 80, 80);
        redSpriteRoll[0] = new TextureRegion(new Texture("RedBac1.png"),128,128);
        redSpriteRoll[1] = new TextureRegion(new Texture("RedBac2.png"),128,128);
        redSpriteRoll[2] = new TextureRegion(new Texture("RedBac3.png"),128,128);
        redSpriteRoll[3] = new TextureRegion(new Texture("RedBac4.png"),128,128);
        redSpriteRoll[4] = new TextureRegion(new Texture("RedBac5.png"),128,128);
        //rolls[roll] = new Animation(ANIMATION_SPEED, rollSpriteSheet[0]);
        rolls[roll] = new Animation(ANIMATION_SPEED, redSpriteRoll);


        //image = new Texture("Karsa.jpg");
    }

    @Override
    public void update(float deltaTime, float gravity) {
        /*if(Gdx.input.isKeyPressed(Keys.SPACE)){
            this.velocityY += JUMP_VELOCITY * getweight();
            //We multiply by deltaTime below to reduce the max height of the jump
            //IF not the jump would just kind continue?
        }else if(Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY >0){
            this.velocityY += JUMP_VELOCITY * getweight() * deltaTime;
        }*/
        super.update(deltaTime, gravity); //This is what applys the gravity

        //Here we use negative speed as we are moving char to the left
        if(Gdx.input.isKeyPressed(Keys.LEFT))
            moveX(-SPEED * deltaTime);

        if(Gdx.input.isKeyPressed(Keys.RIGHT))
            moveX(SPEED * deltaTime);

        if(Gdx.input.isKeyPressed(Keys.UP))
            moveY(SPEED * deltaTime);

        if(Gdx.input.isKeyPressed(Keys.DOWN))
            moveY(-SPEED * deltaTime);

        //System.out.println("Cell loc" + this.getX());
    }

    @Override
    public void render(SpriteBatch batch) {
        //Here we specify where the player is to be spawned (pos.x pos.y)
        //The getWidth and getHeight specify the dimensions that the player should occupy
        //batch.draw(image, pos.x, pos.y, getWidth(), getHeight());

        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) rolls[roll].getKeyFrame((float) stateTime,true), pos.x, pos.y,getWidth(),getHeight());
    }

    public void changeForm(){
        boundSpores++;
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("BacteriaRed.png"), 201, 130);
        rolls[roll] = new Animation(ANIMATION_SPEED, rollSpriteSheet[0]);
    }

    public void makeEnzyme(){
        System.out.println("Here is where we make the killer enzyme.");
    }

    public int getSporeCount(){
        return boundSpores;
    }
}

