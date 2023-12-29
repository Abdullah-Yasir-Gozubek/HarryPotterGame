package game.BackpackItems;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Enemies.LastLevelEnemy;

public class ExplosionCollision implements CollisionListener {

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof LastLevelEnemy) {
            LastLevelEnemy enemy = (LastLevelEnemy) e.getOtherBody();
            enemy.setHealth(enemy.getHealth() - 100);

            if (enemy.getHealth() <= 0) {
                enemy.destroy();
            }
        }
    }
}
