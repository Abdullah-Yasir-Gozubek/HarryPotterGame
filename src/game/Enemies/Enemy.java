package game.Enemies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Enemy extends Walker implements StepListener {

    protected static final Shape enemyShape = new PolygonShape(
            -1.14f,-1.9f, -1.89f,0.85f, -0.31f,1.98f, 0.87f,1.29f, 1.86f,0.36f, 0.34f,-1.92f);

    private static final BodyImage image = new BodyImage("data/Enemies/enemy.png", 4f);

    private static SoundClip enemySound;

    static {
        try {
            enemySound = new SoundClip("data/Sound/enemyHit.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    private final int SPEED = 4;

    private float left, right;
    private final int RANGE = 7;
    private int health;


    public Enemy(World world) {
        super(world, enemyShape);
        addImage(image);
        world.addStepListener(this);
        startWalking(SPEED);
        this.health = 100;

    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
        left = position.x-RANGE;
        right = position.x+RANGE;
    }

    @Override
    public void destroy()
    {
        enemySound.play();
        super.destroy();
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        float dx = getLinearVelocity().x;

        if (dx < 0) {
            removeAllImages();
            addImage(new BodyImage("data/Enemies/enemyLeft.png", 4f));
        } else if (dx > 0) {
            removeAllImages();
            addImage(new BodyImage("data/Enemies/enemyRight.png", 4f));
        }

        if (getPosition().x > right){
            startWalking(-SPEED);
        }
        if (getPosition().x < left){
            startWalking(SPEED);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

}


