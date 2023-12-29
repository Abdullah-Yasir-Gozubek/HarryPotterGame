package game.Enemies;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.GameLevel;
import game.Players.Player;

public class LastLevelEnemyCollision implements CollisionListener {

    GameLevel currentLevel;
    private Player player;
    Game game;

    public LastLevelEnemyCollision(Player player, GameLevel level, Game game) {
        this.player = player;
        currentLevel = level;
        this.game = game;
    }

    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof LastLevelEnemy) {
            player.setHealth(player.getHealth() - 100);

            if (player.getHealth() <= 0) {
                game.setGameOver(true);
            }
        }
    }


}
