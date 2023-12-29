package game.Enemies;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.GameLevel;
import game.Players.Player;

public class EnemyEncounter implements CollisionListener {

    GameLevel currentLevel;
    private Player player;
    Game game;

    public EnemyEncounter(Player player, GameLevel level, Game game) {
        this.player = player;
        currentLevel = level;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) {
            player.setHealth(player.getHealth() - 100);

            if (e.getOtherBody() instanceof LastLevelEnemy) {
                LastLevelEnemy lastLevelEnemy = (LastLevelEnemy) e.getOtherBody();
                lastLevelEnemy.setHealth(lastLevelEnemy.getHealth() - 25);

                if (lastLevelEnemy.getHealth() <= 0) {
                    e.getOtherBody().destroy();
                }
            } else {
                e.getOtherBody().destroy();
            }

            if (player.getHealth() <= 0) {
                game.setGameOver(true);
            }
        }
    }
}