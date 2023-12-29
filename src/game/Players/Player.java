package game.Players;

import city.cs.engine.*;
import game.BackpackItems.Backpack;
import game.Enemies.Enemy;
import game.BackpackItems.EnemyBomb;
import game.Enemies.LastLevelEnemy;
import org.jbox2d.common.Vec2;

import java.util.List;

public class Player extends Walker {



    private static final Shape playerShape = new PolygonShape(
            -0.11f,2.8f,
            0.87f,1.48f,
            0.99f,0.29f,
            0.24f,-2.32f,
            -1.12f,-2.27f,
            -1.24f,1.21f);

    private static final BodyImage leftImage =
            new BodyImage("data/playerLeft.png", 5f);

    private static final BodyImage rightImage =
            new BodyImage("data/playerRight.png", 5f);


    private int coinCount;
    private int credits;
    private String direction;
    private AttachedImage aImage;
    private int health;
    private Backpack backpack;



    public Player(World world) {
        super(world, playerShape);
        aImage = addImage(leftImage);
        direction = "left";
        coinCount = 0;
        this.health = 100;

        backpack = new Backpack();

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addCoins(){
        coinCount++;
        System.out.println("Becoming wiser: " +
                "coinCount = " + coinCount);
    }
    public int getCoinCount(){
        return coinCount;
    }


    public void setCoinCount(int coinCount) {
    }



    public void addCredits(){
        credits++;
        System.out.println("Credits: " +
                + credits);
    }


    public int getCredits(){
        return credits;
    }

    @Override
    public void startWalking(float speed){
        super.startWalking(speed);
        if (speed < 0 && direction.equals("right")) {

            List<AttachedImage> allImages = this.getImages();
            for (AttachedImage im : allImages){
                im.flipHorizontal();
            }

            direction = "left";
        }
        else if (speed > 0 && direction.equals("left")){

            List<AttachedImage> allImages = this.getImages();
            for (AttachedImage im : allImages){
                im.flipHorizontal();
            }
            direction = "right";
        }
    }

    public void shoot() {
        DynamicBody projectile = new DynamicBody(this.getWorld(), new CircleShape(0.2f));

        BodyImage image = new BodyImage("data/MagicBall.png", 0.4f);

        if (direction.equals("left")) {
            projectile.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(-25, 3));
        } else {
            projectile.setPosition(new Vec2(this.getPosition().x + 1, this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(25, 3));
        }

        projectile.addImage(image);
        projectile.addCollisionListener(new CollisionListener() {
            @Override
            public void collide(CollisionEvent e) {
                if (e.getOtherBody() instanceof Enemy) {
                    e.getOtherBody().destroy();
                    projectile.destroy();
                } else if (e.getOtherBody() instanceof StaticBody) {
                    projectile.destroy();
                }else if (e.getOtherBody() instanceof LastLevelEnemy) {
                    LastLevelEnemy lastLevelEnemy = (LastLevelEnemy) e.getOtherBody();
                    lastLevelEnemy.setHealth(lastLevelEnemy.getHealth() - 10);
                    projectile.destroy();

                    // Check if the enemy's health is less than or equal to 0, and destroy it if true.
                    if (lastLevelEnemy.getHealth() <= 0) {
                        lastLevelEnemy.destroy();
                    }
                }
            }
        });
    }


    public Backpack getBackpack(){
        return  backpack;
    }

    public void dropBomb(){
        new EnemyBomb(this.getWorld(), this.getPosition(),3000);
    }


    public String getDirection(){
        return direction;
    }
}