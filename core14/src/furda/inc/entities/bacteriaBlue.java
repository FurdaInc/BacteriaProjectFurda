package furda.inc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import furda.inc.world.GameMap;


public class bacteriaBlue extends Entity {

    private static final int SPEED = 80;
    private static final int JUMP_VELOCITY = 1;



    public static  final float ANIMATION_SPEED = 0.5f;
    Animation[] rolls;
    int roll;
    double stateTime = Math.random();

    int boundSpores;




    Texture image;
    TextureRegion[] blueSpriteRoll = new TextureRegion[5];

    public bacteriaBlue(float x, float y, GameMap map) {

        super(x, y, EntityType.BACTERIABLUE, map);
        //Intializes the picture of the player
        roll = 1;
        rolls = new Animation[2];
        boundSpores = 0;
        //TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("Bacteria.png"), 201, 130);
        //rolls[roll] = new Animation(ANIMATION_SPEED, blueSpriteRoll);
        blueSpriteRoll[0] = new TextureRegion(new Texture("BlueBacteria1.png"),128,128);
        blueSpriteRoll[1] = new TextureRegion(new Texture("BlueBacteria2.png"),128,128);
        blueSpriteRoll[2] = new TextureRegion(new Texture("BlueBacteria3.png"),128,128);
        blueSpriteRoll[3] = new TextureRegion(new Texture("BlueBacteria4.png"),128,128);
        blueSpriteRoll[4] = new TextureRegion(new Texture("BlueBacteria5.png"),128,128);
        rolls[roll] = new Animation(ANIMATION_SPEED, blueSpriteRoll);


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
        //System.out.println(pos.x + " " + pos.y);

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

    @Override
    public void playAnimation() {

    }

    public void changeForm(){
        boundSpores++;
        if(bottomLeft){

        }
        if(bottomRight){

        }
        if(topLeft){

        }
        if(topRight){

        }
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("BacteriaRed.png"), 201, 130);
        rolls[roll] = new Animation(ANIMATION_SPEED, rollSpriteSheet[0]);
    }

    public int getSporeCount(){
        return boundSpores;
    }
}
