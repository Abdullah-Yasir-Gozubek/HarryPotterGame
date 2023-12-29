package game.Others;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Lift extends StaticBody implements StepListener {

    private static final Shape liftShape = new BoxShape(4.5f, 0.6f);

    private static final BodyImage image = new BodyImage("data/StaticBodies/lift.png", 2f);

    private Vec2 startPosition;
    private float top, bottom;
    private float delta;


    public Lift(World w) {
        super(w, liftShape);
        addImage(image);
        startPosition = new Vec2(15, -10);;
        bottom = startPosition.y;
        top = startPosition.y+20;
        delta=0.08f;
        w.addStepListener(this);

    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().y < bottom){
            this.setPosition(startPosition);
            delta*=-1;
        }
        if (getPosition().y > top){
            delta*=-1;
        }
        this.setPosition(new Vec2(this.getPosition().x, this.getPosition().y+delta));
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

}
