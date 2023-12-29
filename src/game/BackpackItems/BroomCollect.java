package game.BackpackItems;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class BroomCollect extends StaticBody{

    private static final Shape BroomCollectShape = new CircleShape(1f);

    private static final BodyImage image =
            new BodyImage("data/broom.png", 4);


    private static SoundClip BroomCollectSound;


    static {
        try {
            BroomCollectSound = new SoundClip("data/Sound/Portal.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public BroomCollect(World w) {
        super(w,BroomCollectShape);
        addImage(image);

    }


}
