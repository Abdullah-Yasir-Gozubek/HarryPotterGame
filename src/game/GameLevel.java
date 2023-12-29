package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import game.BackpackItems.*;
import game.Coins.CoinsPickup;
import game.Enemies.*;
import game.Levels.Level1;
import game.Levels.Level2;
import game.Levels.Level3;
import game.Players.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public abstract class GameLevel extends World {

    private Game game;
    private Player player;
    private Enemy enemy;
    private SecondEnemy secondEnemy;
    private FlyingEnemy flyingEnemy;
    private SoundClip levelMusic;
    private Portal portal;
    private TrapBox trapbox;
    private BroomCollect broomCollect;
    private WandCollect wandCollect;
    // private EnemyBombCollect enemyBombCollect;
    private ExtraLive extraLive;
    private float volume;


    public GameLevel(Game game){
        super();
        this.game = game;

        try {
            if (this instanceof Level1) {
                levelMusic = new SoundClip("data/Sound/Level1.mp3");
                levelMusic.loop();
            } else if (this instanceof Level2) {
                levelMusic = new SoundClip("data/Sound/Level2.mp3");
                levelMusic.loop();

            } else if (this instanceof Level3) {
                levelMusic = new SoundClip("data/Sound/Level3.mp3");
                levelMusic.loop();

            }
            // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            //code in here will deal with any errors
            //that might occur while loading/playing sound
            System.out.println(e);
        }




////

        setGravity(25);
    }




    public Player getPlayer(){
        return player;
    }
    public Enemy getEnemy(){
        return enemy;
    }
    public FlyingEnemy getFlyingEnemy(){
        return flyingEnemy;
    }
    public SecondEnemy getSecondEnemy(){
        return secondEnemy;
    }
    public Portal getPortal(){return portal;}
    public BroomCollect getBroomCollect(){return broomCollect;}
    public WandCollect getWandCollect(){return wandCollect;}
    //  public EnemyBombCollect getEnemyBombCollect(){return enemyBombCollect;}
    public ExtraLive getExtraLive(){return extraLive;}



    public TrapBox getTrapBox(){
        return trapbox;
    }

    public abstract boolean isComplete();

    public abstract Image[] getBackgrounds();

    public abstract String getName();

    public SoundClip getLevelMusic() {return levelMusic;}
    public void populate() {
        player = new Player(this);
        CoinsPickup pickup = new CoinsPickup(game);
        player.addCollisionListener(pickup);

        player.getBackpack().addItem(new Wand(player));
        player.getBackpack().addItem(new Broom(player));

        EnemyBombCollision ebc = new EnemyBombCollision(player);
        player.addCollisionListener(ebc);

        ExplosionCollision ecbomb = new ExplosionCollision();
        player.addCollisionListener(ecbomb);

        player.addCollisionListener(new LastLevelEnemyCollision(player, this, game));


        BroomCollision broomCollision= new BroomCollision(player);
        player.addCollisionListener(broomCollision);


        EnemyEncounter pe = new EnemyEncounter(player, this, game);
        player.addCollisionListener(pe);


        portal = new Portal( this);


        ExtraLiveCollision as = new ExtraLiveCollision(player);
        player.addCollisionListener(as);

        WandCollision wandCollision= new WandCollision(player);
        player.addCollisionListener(wandCollision);

        PortalCollision pc = new PortalCollision(player, this, game);
        player.addCollisionListener(pc);
    }

    public void stop() {
        super.stop();
        if (levelMusic != null) {
            levelMusic.stop();
            levelMusic.close();
        }
    }
}
