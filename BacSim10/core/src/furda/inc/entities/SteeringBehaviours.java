package furda.inc.entities;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;

public class SteeringBehaviours extends SteeringBehavior {

    public static enum SteeringState {WANDER,SEEK,FLEE,ARRIVE,NONE}

    public SteeringState currentMode = SteeringState.WANDER;

    public SteeringBehaviours(Steerable owner) {
        super(owner);
    }

    public SteeringBehaviours(Steerable owner, Limiter limiter) {
        super(owner, limiter);
    }

    public SteeringBehaviours(Steerable owner, boolean enabled) {
        super(owner, enabled);
    }

    public SteeringBehaviours(Steerable owner, Limiter limiter, boolean enabled) {
        super(owner, limiter, enabled);
    }

    @Override
    protected SteeringAcceleration calculateRealSteering(SteeringAcceleration steering) {
        return null;
    }
}
