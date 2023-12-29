package game.BackpackItems;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EnemyBomb implements ActionListener {

    private static final BodyImage bombImage =
            new BodyImage("data/MagicBall.png", 3f);
    private static final BodyImage explosionImage =
            new BodyImage("data/ExpoImage.png", 6f);

    private DynamicBody enemyBomb;
    private int counter;
    private DynamicBody explosion;
    private static SoundClip checkSound;
    private static SoundClip expoSound;

    static {
        try {
            checkSound = new SoundClip("data/Sound/checkSound.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    static {
        try {
            expoSound = new SoundClip("data/Sound/explosion.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public EnemyBomb(World world, Vec2 pos, int detonationTime) {

        enemyBomb = new DynamicBody(world, new CircleShape(1));
        enemyBomb.addImage(bombImage);
        enemyBomb.setPosition(pos);

        checkSound.play();

        javax.swing.Timer t = new javax.swing.Timer(detonationTime, this);
        t.setRepeats(false);
        t.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (counter == 0) {

            enemyBomb.destroy();
            checkSound.stop();

            explosion = new DynamicBody(enemyBomb.getWorld(), new CircleShape(3));
            explosion.addImage(explosionImage);
            explosion.setPosition(enemyBomb.getPosition());

            expoSound.play();


            javax.swing.Timer t2 = new javax.swing.Timer(300, this);
            t2.setRepeats(false);
            t2.start();
        } else if (counter == 1) {
            explosion.destroy();
        }
        counter++;
    }

}
