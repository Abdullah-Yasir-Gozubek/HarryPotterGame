package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Players.Player;

public class PortalCollision implements CollisionListener {

    GameLevel currentLevel;
    private Player player;
    Game game;

    public PortalCollision(Player player, GameLevel level, Game game) {
        this.player = player;
        currentLevel = level;
        this.game = game;


    }


    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Portal) {

            if (currentLevel.isComplete())
                game.goToNextLevel();

        }
    }
}
