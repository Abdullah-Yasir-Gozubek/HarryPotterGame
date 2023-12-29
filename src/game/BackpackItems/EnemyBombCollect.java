package game.BackpackItems;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EnemyBombCollect extends StaticBody{

    private static final Shape EnemyBombCollectShape = new CircleShape(1f);

    private static final BodyImage image =
            new BodyImage("data/MagicBall.png", 3f);


    private static SoundClip EnemyBombCollectSound;


    static {
        try {
            EnemyBombCollectSound = new SoundClip("data/Sound/Portal.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public EnemyBombCollect(World w) {
        super(w,EnemyBombCollectShape);
        addImage(image);

    }

}
