package game.BackpackItems;

import city.cs.engine.BodyImage;
import game.Players.Player;
import org.jbox2d.common.Vec2;

import java.util.prefs.Preferences;

public class Broom extends BackpackItem {
    private boolean active;
    private Preferences prefs;


    public Broom(Player player) {
        super(player);
        image = new BodyImage("data/broom.png", 4.5f);

        prefs = Preferences.userNodeForPackage(getClass());
        active = prefs.getBoolean("active2", false);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            prefs.remove("active2");
        }));
    }



    @Override
    public String getType() {
        return "Broom";
    }

    @Override
    public void operate() {
        if (active) {
            player.setLinearVelocity(new Vec2(0, 5));
        }
    }

    public void putOn(){
        if (active) {
            aImage = player.addImage(image);
            aImage.setOffset(new Vec2(0,-1));

            if (player.getDirection().equals("left"))
                aImage.flipHorizontal();
        }
    }

    public void setActive(boolean active) {
        this.active = active;
        prefs.putBoolean("active2", active);
    }

    public boolean isActive() {
        return active;
    }
}
