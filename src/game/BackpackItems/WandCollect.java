package game.BackpackItems;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class WandCollect extends StaticBody{

    private static final Shape WandCollectShape = new CircleShape(1f);

    private static final BodyImage image =
            new BodyImage("data/wand.png", 2);


    private static SoundClip WandCollectSound;


    static {
        try {
            WandCollectSound = new SoundClip("data/Sound/Portal.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public WandCollect(World w) {
        super(w,WandCollectShape);
        addImage(image);

    }

}
