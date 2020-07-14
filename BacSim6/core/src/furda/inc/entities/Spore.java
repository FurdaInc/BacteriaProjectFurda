package furda.inc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import furda.inc.world.GameMap;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spore extends Entity implements Steerable<Vector2> {

    Texture image;

    float Direction;
    Vector2 linearVelocity;
    float angularVelocity;
    boolean independentFacing;
    SteeringBehavior<Vector2> steeringBehavior;



    private static final int SPEED = 80;

    public static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());

    public Spore(float x, float y, GameMap map) {
        super(x, y, EntityType.SPORE, map);
        image = new Texture("Spore1.png");

        //Wander<Vector2> wander = new Wander<Vector2>(this);
        //steeringBehavior = new Wander<Vector2>(this);
        //wander.setFaceEnabled(true);
        //wander.setWanderRate(SPEED);
        //wander.setEnabled(true);


    }

    //public static Wander<Vector2> getWander



    @Override
    public void update(float deltaTime, float gravity) {
        if (steeringBehavior != null){
            steeringBehavior.calculateSteering(steeringOutput);

            //Put special conditions here if there are any

            applySteering(steeringOutput, deltaTime);
        }


    }

    private void applySteering (SteeringAcceleration<Vector2> steering, float time){
        this.pos.mulAdd(linearVelocity, time);
        this.linearVelocity.mulAdd(steering.linear, time).limit(this.getMaxLinearSpeed());

        if(independentFacing){
            this.Direction += angularVelocity * time;
            this.angularVelocity += steering.angular * time;
        }else{
            float newOrientation = calculateOrientationFromVelocity(this);
            if (newOrientation != this.Direction){
                this.angularVelocity = (newOrientation - this.Direction) * time;
                this.Direction = newOrientation;
            }
        }
    }


    public static float calculateOrientationFromVelocity (Steerable character){
        if (character.getLinearVelocity().isZero(character.getZeroLinearSpeedThreshold()))
            return character.getOrientation();
        return character.vectorToAngle(character.getLinearVelocity());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }


    @Override
    public Vector2 getPosition() {
        return null;
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float)Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float)Math.sin(angle);
        outVector.y = (float)Math.cos(angle);
        return outVector;
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return null;
    }

    @Override
    public float getAngularVelocity() {
        return 0;
    }

    @Override
    public float getBoundingRadius() {
        return 0;
    }

    @Override
    public boolean isTagged() {
        return false;
    }

    @Override
    public void setTagged(boolean tagged) {

    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return 0;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {

    }

    @Override
    public float getMaxLinearSpeed() {
        return 0;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {

    }

    @Override
    public float getMaxLinearAcceleration() {
        return 0;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {

    }

    @Override
    public float getMaxAngularSpeed() {
        return 0;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {

    }

    @Override
    public float getMaxAngularAcceleration() {
        return 0;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {

    }
}
