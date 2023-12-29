package game.BackpackItems;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Players.Player;

public class WandCollision implements CollisionListener {

    private Player player;

    public WandCollision(Player player) {
        this.player = player;
    }



    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof WandCollect) {
            // Set the broom item to active
            player.getBackpack().getWand().setActive(true);


            e.getOtherBody().destroy();
        }
    }
}
