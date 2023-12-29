package game.Levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.BackpackItems.BroomCollect;
import game.Coins.Coins;
import game.Enemies.FlyingEnemy;
import game.Enemies.TrapBox;
import game.ExtraLive;
import game.Game;
import game.GameLevel;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Level2 extends GameLevel {


    private int[] x;
    private int thirdImageX;
    private Image[] backgrounds;
    private FlyingEnemy flyingEnemy;
    private TrapBox trapbox;
    private BroomCollect broomCollect;
    private ExtraLive extraLive;
    private int enemiesAlive;


    Game game;
    public Level2(Game game){
        super(game);
        this.game = game;
        enemiesAlive = 1;

        backgrounds = new Image[3];
        backgrounds[0] = new ImageIcon("data/Backgrounds/Level2/1.jpg").getImage();
        backgrounds[1] = new ImageIcon("data/Backgrounds/Level2/2.png").getImage();
        backgrounds[2] = new ImageIcon("data/Backgrounds/Level2/3.png").getImage();

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
        platform1.setPosition(new Vec2(-6, 5.5f));
        platform1.addImage(new BodyImage("data/StaticBodies/platform1.png", 4f));

        Shape platform2Shape = new BoxShape(2.7f, 1.8f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(-15, -2));
        platform2.addImage(new BodyImage("data/StaticBodies/platform2.png", 4f));

        Shape platform3Shape = new BoxShape(2.65f, 1.8f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(-4, -6));
        platform3.addImage(new BodyImage("data/StaticBodies/platform3.png", 4f));

        Shape platform4Shape = new BoxShape(2.65f, 1.8f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setPosition(new Vec2(14.5f, 7));
        platform4.addImage(new BodyImage("data/StaticBodies/platform3.png", 4f));

        Shape platform5Shape = new BoxShape(2.3f, 1.5f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setPosition(new Vec2(10, -4.3f));
        platform5.addImage(new BodyImage("data/StaticBodies/platform2.png", 3f));



    }

    @Override
    public void populate() {
        super.populate();

        getPlayer().setPosition(new Vec2(-4, -5));

        flyingEnemy = new FlyingEnemy(this);
        flyingEnemy.setPosition(new Vec2(7, 12));

        new Coins(this).setPosition(new Vec2(-6,6.5f));
        new Coins(this).setPosition(new Vec2(-15,0));


        trapbox = new TrapBox(this, game);
        trapbox.setPosition(new Vec2(17.9f, -10.3f));
        trapbox = new TrapBox(this,game);
        trapbox.setPosition(new Vec2(10.4f, -10.3f));
        trapbox = new TrapBox(this,game);
        trapbox.setPosition(new Vec2(3, -10.3f));
        trapbox = new TrapBox(this,game);
        trapbox.setPosition(new Vec2(-4.4f, -10.3f));
        trapbox = new TrapBox(this,game);
        trapbox.setPosition(new Vec2(-11.8f, -10.3f));
        trapbox = new TrapBox(this,game);
        trapbox.setPosition(new Vec2(-19.2f, -10.3f));


        new BroomCollect(this).setPosition(new Vec2(11,0f));

        new ExtraLive(this).setPosition(new Vec2(16,-4));
        new ExtraLive(this).setPosition(new Vec2(-11,-8));




        getPortal().setPosition(new Vec2(14.5f, 9.8f));
//        getTrapBox().setPosition(new Vec2(14.5f,-10.3f));
//        getTrapBox().setPosition(new Vec2(4.5f,-10.3f));

        flyingEnemy.addDestructionListener(new DestructionListener() {
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
        return "level2";
    }


}
