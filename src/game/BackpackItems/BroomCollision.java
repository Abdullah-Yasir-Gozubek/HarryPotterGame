package game.BackpackItems;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Players.Player;

public class BroomCollision implements CollisionListener {

    private Player player;

    public BroomCollision(Player player) {
        this.player = player;
    }



    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof BroomCollect) {
            // Set the broom item to active
            player.getBackpack().getBroom().setActive(true);

            // Destroy the BroomCollect object
            e.getOtherBody().destroy();
        }
    }
}
