package game.BackpackItems;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Players.Player;

public class EnemyBombCollision implements CollisionListener {

    private Player player;

    public EnemyBombCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {


    }
}