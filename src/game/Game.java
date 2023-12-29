package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import game.Levels.Level1;
import game.Levels.Level2;
import game.Levels.Level3;
import game.Others.GiveFocus;
import game.Players.Player;
import game.Players.PlayerController;

import javax.swing.*;
import java.awt.*;


public class Game {
    public GameLevel currentLevel;
    GameView view;
    private int coinCount;
    PlayerController controller;
    //    private SoundClip gameMusic;
    private SoundClip currentMusic;

    private float musicVolume = 0.5f;

    private boolean gameOver;
    private boolean menuVisible;
    private ControlPanel controlPanel;
    private SubmenuPanel submenuPanel;

    private Frame frame;
    private SettingsPanel settingsPanel;
    JPanel menuPanel;
    World world;


    public Game() {

        gameOver = false;
        menuVisible = false;

        currentLevel = new Level1(this);
        currentLevel.populate();
        view = new GameView(currentLevel, this,800, 600);


        controller = new PlayerController(currentLevel.getPlayer(), this);
        view.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(view));

        final JFrame frame = new JFrame("Harry Potter Game");

        ControlPanel controlPanel = new ControlPanel(this); // pass a reference to the game instance
        frame.add(controlPanel.getMainPanel(), BorderLayout.CENTER);

        //menuPanel.setPreferredSize(new Dimension(800, 600));
        // frame.add(view);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Start the game once the play button has been clicked
        //currentLevel.start();

    }


    public void restartCurrentLevel() {
        if (currentLevel instanceof Level1) {
            currentLevel.stop();

            setLevel(new Level1(this));
            currentLevel.populate();
            setLevelMusic(currentLevel);
            view.setWorld(currentLevel);
            controller.updatePlayer(currentLevel.getPlayer());
            currentLevel.start();

        } else if (currentLevel instanceof Level2) {
            currentLevel.stop();

            currentLevel = new Level2(this);
            setLevel(new Level2(this));
            currentLevel.populate();
            setLevelMusic(currentLevel);
            view.setWorld(currentLevel);
            controller.updatePlayer(currentLevel.getPlayer());
            currentLevel.start();

        } else if (currentLevel instanceof Level3) {
            currentLevel.stop();

            currentLevel = new Level3(this);
            setLevel(new Level3(this));
            currentLevel.populate();
            setLevelMusic(currentLevel);
            view.setWorld(currentLevel);
            controller.updatePlayer(currentLevel.getPlayer());
            currentLevel.start();
        }
    }


    public void setMusicVolume(float volume) {
        this.musicVolume = volume;
        if (currentLevel != null) {
            currentLevel.getLevelMusic().setVolume(musicVolume);
        }
    }


    public void setLevelMusic(GameLevel level) {
        // stop current music if playing
        if (currentMusic != null) {
            currentMusic.stop();
        }

        if (level != null) {
            currentMusic = level.getLevelMusic();
            currentMusic.play();
            currentMusic.loop();
        }
    }


    public void setLevel(GameLevel level){
        currentLevel.stop();
        currentLevel = level;
        setLevelMusic(currentLevel);
        view.setWorld(currentLevel);
        controller.updatePlayer(currentLevel.getPlayer());
        currentLevel.start();
    }


    public void goToNextLevel() {

        if (currentLevel instanceof Level1) {
            currentLevel.stop();
            currentLevel = new Level2(this);
            currentLevel.populate();
            setLevelMusic(currentLevel);
            view.setWorld(currentLevel);
            controller.updatePlayer(currentLevel.getPlayer());
            currentLevel.start();
        } else if (currentLevel instanceof Level2) {
            currentLevel.stop();
            currentLevel = new Level3(this);
            currentLevel.populate();
            setLevelMusic(currentLevel);
            view.setWorld(currentLevel);
            controller.updatePlayer(currentLevel.getPlayer());
            currentLevel.start();
        } else if (currentLevel instanceof Level3) {
            currentLevel.stop();
            JOptionPane.showMessageDialog(view, "Congratulations! You have finished the game.", "Game Done", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }



    public void addCoins(){
        coinCount++;
    }
    public int getCoinsCount(){
        return  coinCount;
    }

    public Player getPlayer() {
        return currentLevel.getPlayer();
    }


    public static void main(String[] args) {

        new Game();
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void setGameOver(boolean over){
        gameOver = over;
        currentLevel.stop();
        view.repaint();
    }



    public void toggleMenu() {
        view.toggleSubmenuPanel();
    }



}