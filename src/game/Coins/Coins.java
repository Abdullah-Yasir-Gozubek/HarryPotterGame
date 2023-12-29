package game.Coins;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coins extends DynamicBody {

    private static final Shape coinsShape = new CircleShape(1.2f);

    private static final BodyImage image =
            new BodyImage("data/coins.png", 2f);

    private static SoundClip coinsSound;

    static {
        try {
            coinsSound = new SoundClip("data/Sound/coinSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Coins(World w) {
        super(w,coinsShape);
        addImage(image);

    }

    @Override
    public void destroy()
    {
        coinsSound.play();
        super.destroy();
    }


}