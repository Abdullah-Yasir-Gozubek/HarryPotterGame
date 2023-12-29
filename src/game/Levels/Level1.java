package game.Levels;

import city.cs.engine.Shape;
import city.cs.engine.*;
import game.BackpackItems.EnemyBombCollect;
import game.BackpackItems.WandCollect;
import game.Coins.Coins;
import game.Enemies.Enemy;
import game.Enemies.FlyingEnemy;
import game.Enemies.SecondEnemy;
import game.Enemies.TrapBox;
import game.Game;
import game.GameLevel;
import game.Others.Lift;
import game.Players.Player;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Level1 extends GameLevel {

    private int[] x;
    private int thirdImageX;
    private Image[] backgrounds;
    private SoundClip music;
    private Enemy enemy;
    private SecondEnemy secondEnemy;
    private FlyingEnemy flyingEnemy;
    private TrapBox trapbox;
    private WandCollect wandCollect;
    private EnemyBombCollect enemyBombCollect;
    private Player player;
    private int enemiesAlive;




    Game game;


    public Level1(Game game){
        super(game);
        this.game = game;

        getLevelMusic().setVolume(0.5f); // set the volume for this level's music
        enemiesAlive = 3;
        backgrounds = new Image[3];
        backgrounds[0] = new ImageIcon("data/Backgrounds/Level1/1.png").getImage();
        backgrounds[1] = new ImageIcon("data/Backgrounds/Level1/2.png").getImage();
        backgrounds[2] = new ImageIcon("data/Backgrounds/Level1/3.png").getImage();

        x = new int[backgrounds.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
        }
        thirdImageX = 0;


        Shape shape = new BoxShape(40, 0.4f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(-1.2f, -11.8f));
        ground.addImage(new BodyImage("data/StaticBodies/ground2.png", 6.4f));


        Shape platform1Shape = new BoxShape(3.2f, 1.8f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-2, 4f));
        platform1.addImage(new BodyImage("data/StaticBodies/platform1.png", 4f));

        Shape platform2Shape = new BoxShape(2.7f, 1.8f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(8, 0));
        platform2.addImage(new BodyImage("data/StaticBodies/platform2.png", 4f));

        Shape platform3Shape = new BoxShape(2.65f, 1.8f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(-12, 0));
        platform3.addImage(new BodyImage("data/StaticBodies/platform3.png", 4f));

    }

    @Override
    public void populate() {
        super.populate();
        //  player = new Player(this);
        //player.setPosition(new Vec2(-16, -10));
        Lift lift = new Lift(this);
        lift.setPosition(new Vec2(15, -10));

        getPlayer().setPosition(new Vec2(-16, -10));

        enemy = new Enemy(this);
        enemy.setPosition(new Vec2(0,-10));

        flyingEnemy = new FlyingEnemy(this);
        flyingEnemy.setPosition(new Vec2(-12,8));

        secondEnemy = new SecondEnemy(this);
        secondEnemy.setPosition(new Vec2(15, 1));

        new Coins(this).setPosition(new Vec2(-2,8f));
        new Coins(this).setPosition(new Vec2(8,5f));
        //new Coins(this).setPosition(new Vec2(18.988f,-10));

        trapbox = new TrapBox(this, game);
        trapbox.setPosition(new Vec2(14.5f,-10.3f));



        // new EnemyBombCollect(this).setPosition(new Vec2(-9,-8));
        new WandCollect(this).setPosition(new Vec2(6,8));


        getPortal().setPosition(new Vec2(-12, 4));


        enemy.addDestructionListener(new DestructionListener() {
            @Override
            public void destroy(DestructionEvent destructionEvent) {
                enemiesAlive--;
            }
        });

        flyingEnemy.addDestructionListener(new DestructionListener() {
            @Override
            public void destroy(DestructionEvent destructionEvent) {
                enemiesAlive--;
            }
        });

        secondEnemy.addDestructionListener(new DestructionListener() {
            @Override
            public void destroy(DestructionEvent destructionEvent) {
                enemiesAlive--;
            }
        });
    }
    @Override
    public boolean isComplete() {
        return enemiesAlive <= 0;
    }
    @Override
    public Image[] getBackgrounds() {
        return backgrounds;
    }

    @Override
    public String getName(){
        return "level1";
    }


}
