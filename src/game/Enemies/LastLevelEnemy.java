package game.Enemies;

import city.cs.engine.*;
import game.Players.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class LastLevelEnemy extends Walker implements StepListener {

    protected static final Shape lastLevelEnemyShape = new PolygonShape(
            -3.87f,-4.72f, -3.87f,0.04f, -1.09f,4.94f, 2.63f,3.02f, 3.87f,-4.24f);

    private static final BodyImage[] images = {
            new BodyImage("data/Enemies/LastLevelEnemy/left.png", 10f),
            new BodyImage("data/Enemies/LastLevelEnemy/right.png", 10f),
            new BodyImage("data/Enemies/LastLevelEnemy/leftRange.png", 7f),
            new BodyImage("data/Enemies/LastLevelEnemy/rightRange.png", 7f),
    };
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
    private final int RANGE = 15;
    private int health;
    private Player player;
    private int animationFrame = 0;

    public LastLevelEnemy(World world, Player player) {
        super(world, lastLevelEnemyShape);
        this.player = player;
        world.addStepListener(this);
        startWalking(SPEED);
        this.health = 100;
        addImage(images[0]);

    }

    public void followPlayer() {
        float playerX = player.getPosition().x;
        float enemyX = getPosition().x;

        float distance = player.getPosition().sub(getPosition()).length();

        if (distance <= RANGE) {
            if (playerX < enemyX) {
                removeAllImages();
                addImage(images[0]);

                startWalking(-SPEED);
            } else if (playerX > enemyX) {
                removeAllImages();
                addImage(images[1]);
                startWalking(SPEED);
            }
        } else {
            stopWalking();
        }
    }

    private void animate() {
        float distance = player.getPosition().sub(getPosition()).length();

        if (distance <= RANGE) {
            if (getLinearVelocity().x < 0) {
                if (getImages().contains(images[2]) == false) {
                    removeAllImages();
                    addImage(images[2]);
                }
            } else if (getLinearVelocity().x > 0) {
                if (getImages().contains(images[3]) == false) {
                    removeAllImages();
                    addImage(images[3]);
                }
            }
        } else {
            if (getLinearVelocity().x < 0) {
                if (getImages().contains(images[0]) == false) {
                    removeAllImages();
                    addImage(images[0]);
                }
            } else if (getLinearVelocity().x > 0) {
                if (getImages().contains(images[1]) == false) {
                    removeAllImages();
                    addImage(images[1]);
                }
            }
        }
    }


    @Override
    public void postStep(StepEvent stepEvent) {
        followPlayer();
        animate();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void destroy() {
        enemySound.play();
        super.destroy();
    }
    @Override
    public void preStep(StepEvent stepEvent) {
    }
}
