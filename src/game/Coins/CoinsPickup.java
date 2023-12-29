package game.Coins;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;

public class CoinsPickup implements CollisionListener {

    private Game game;
    public CoinsPickup(Game g){
        this.game = g;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coins) {
            game.addCoins();
            e.getOtherBody().destroy();
        }
    }
}