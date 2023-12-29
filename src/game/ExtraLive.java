package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ExtraLive extends StaticBody{

    private static final Shape ExtraLiveShape = new CircleShape(1f);

    private static final BodyImage image =
            new BodyImage("data/heart.png", 2);


    private static SoundClip ExtraLiveShapeSound;


    static {
        try {
            ExtraLiveShapeSound = new SoundClip("data/Sound/Portal.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public ExtraLive(World w) {
        super(w,ExtraLiveShape);
        addImage(image);

    }

}
