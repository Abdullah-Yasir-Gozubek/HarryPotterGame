package game.BackpackItems;

import city.cs.engine.BodyImage;
import game.Players.Player;
import org.jbox2d.common.Vec2;

import java.util.prefs.Preferences;

public class Wand extends BackpackItem {

    private boolean active;
    private Preferences prefs;



    public Wand(Player player) {
        super(player);
        image = new BodyImage("data/wand.png", 1.5f);
        prefs = Preferences.userNodeForPackage(getClass());
        active = prefs.getBoolean("active", false);

        // Add a shutdown hook to clear the "active" preference when the game is terminated
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            prefs.remove("active");
        }));

    }

    @Override
    public String getType() {
        return "Wand";
    }

    @Override
    public void operate() {
        if (active) {
            player.shoot();
        }
    }

    public void putOn(){
        if (active) {
            aImage = player.addImage(image);
            aImage.setOffset(new Vec2(1.8f,-1));
            if (player.getDirection().equals("left")) {
                aImage.flipHorizontal();
            }
        }
    }

    public void setActive(boolean active) {
        this.active = active;
        prefs.putBoolean("active", active);

    }

    public boolean isActive() {
        return active;
    }
}
