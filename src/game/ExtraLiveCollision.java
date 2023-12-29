package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Players.Player;

public class ExtraLiveCollision implements CollisionListener {

    private Player player;

    public ExtraLiveCollision(Player player) {
        this.player = player;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof ExtraLive) {
            // Increase player health
            player.setHealth(player.getHealth() + 100);
            System.out.println(player.getHealth());

            // Remove heart from world
            e.getOtherBody().destroy();
        }
    }
}
