package game.Enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class SecondEnemy extends Enemy implements StepListener{

    private final int SPEED = 4;

    private float left, right;
    private final int RANGE = 2;


    private static final BodyImage image = new BodyImage("data/Enemies/enemy2.png", 3.2f);

    public SecondEnemy(World world) {
        super(world);
        removeAllImages();
        addImage(image);
        setGravityScale(5);
    }

    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
        left = position.x-RANGE;
        right = position.x+RANGE;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        float dx = getLinearVelocity().x;

        if (dx < 0) {
            removeAllImages();
            addImage(new BodyImage("data/Enemies/enemyLeft2.png", 3.2f));
        } else if (dx > 0) {
            removeAllImages();
            addImage(new BodyImage("data/Enemies/enemyRight2.png", 3.2f));
        }

        if (getPosition().x > right){
            startWalking(-SPEED);
        }
        if (getPosition().x < left){
            startWalking(SPEED);
        }
    }
}
