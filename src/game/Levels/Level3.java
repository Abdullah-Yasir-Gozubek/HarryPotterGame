package game.Levels;

import city.cs.engine.Shape;
import city.cs.engine.*;
import game.Enemies.LastLevelEnemy;
import game.Enemies.TrapBox;
import game.Game;
import game.GameLevel;
import game.Players.Player;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Level3 extends GameLevel {


    private int[] x;
    private int thirdImageX;
    private Image[] backgrounds;
    private LastLevelEnemy lastLevelEnemy;
    private TrapBox trapbox;
    private Player player;
    Game game;
    private int enemiesAlive;



    public Level3(Game game){
        super(game);
        this.game = game;
        enemiesAlive = 1;

        backgrounds = new Image[3];
        backgrounds[0] = new ImageIcon("data/Backgrounds/Level3/1.jpg").getImage();
        backgrounds[1] = new ImageIcon("data/Backgrounds/Level3/2.png").getImage();
        backgrounds[2] = new ImageIcon("data/Backgrounds/Level3/3.png").getImage();

        x = new int[backgrounds.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
        }
        thirdImageX = 0;


        Shape shape = new BoxShape(40, 0.4f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(-1.2f, -11.8f));
        ground.addImage(new BodyImage("data/StaticBodies/ground3.png", 6.4f));

        Shape platform1Shape = new BoxShape(3.2f, 1.8f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(0, 10));
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
        getPlayer().setPosition(new Vec2(7, 2));



        lastLevelEnemy = new LastLevelEnemy(this, getPlayer());
        lastLevelEnemy.setPosition(new Vec2(0, -2));

       trapbox = new TrapBox(this, game);
        trapbox.setPosition(new Vec2(14.5f, -10.3f));

        trapbox = new TrapBox(this, game);
        trapbox.setPosition(new Vec2(-14.5f, -10.3f));


        getPortal().setPosition(new Vec2(-12, 4));

        lastLevelEnemy.addDestructionListener(new DestructionListener() {
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
        return "level3";
    }

}
