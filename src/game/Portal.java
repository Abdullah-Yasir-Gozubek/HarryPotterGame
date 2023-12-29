package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Portal extends StaticBody{

    private static final Shape portalShape = new CircleShape(0.5f);

    private static final BodyImage image =
            new BodyImage("data/snitch.gif", 3.3f);


    private static SoundClip PortalSound;


    static {
        try {
            PortalSound = new SoundClip("data/Sound/Portal.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Portal(World w) {
        super(w,portalShape);
        addImage(image);

    }

}
