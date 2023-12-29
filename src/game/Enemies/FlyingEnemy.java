package game.Enemies;

import city.cs.engine.*;
import game.Players.Player;
import org.jbox2d.common.Vec2;

public class FlyingEnemy extends Enemy implements StepListener {

    protected static final Shape enemyShape = new PolygonShape(
            -1.14f, -1.9f, -1.89f, 0.85f, -0.31f, 1.98f, 0.87f, 1.29f, 1.86f, 0.36f, 0.34f, -1.92f);

    private static final BodyImage image = new BodyImage("data/Enemies/newFlyingEnemy.png", 4f);

    private static final float FLYING_SPEED = 8f;
    private static final float FLYING_RANGE = 4f;

    private float left, right;

    private boolean flyingRight = true;
    Player player;

    public FlyingEnemy(World world) {
        super(world);
        addImage(image);
        world.addStepListener(this);
        setGravityScale(0);
        startWalking(FLYING_SPEED);

    }

    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
        left = position.x - FLYING_RANGE;
        right = position.x + FLYING_RANGE;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        float dx = getLinearVelocity().x;

        if (dx < 0) {
            removeAllImages();
            addImage(new BodyImage("data/Enemies/flyEnemyLeft.png", 4f));
        } else if (dx > 0) {
            removeAllImages();
            addImage(new BodyImage("data/Enemies/flyEnemyRight.png", 4f));
        }

        if (getPosition().x > right) {
            startWalking(-FLYING_SPEED);
        }
        if (getPosition().x < left) {
            startWalking(FLYING_SPEED);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
