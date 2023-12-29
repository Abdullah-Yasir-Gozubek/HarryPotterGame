package game.Players;

import game.Game;
import game.GameLevel;
import game.GameSaverLoader;
import game.HighScoreReader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PlayerController implements KeyListener {

    private static final float WALKING_SPEED = 6;
    private static final float RUNNING_SPEED = 11;


    private Player player;
    private boolean isRunning;
    Game game;

   // private String speed;

    public PlayerController(Player s, Game g){
        player = s;
        isRunning = false;
        game = g;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT) {
            player.startWalking(-getSpeed());
        } else if (code == KeyEvent.VK_RIGHT) {
            player.startWalking(getSpeed());
        } else if (code == KeyEvent.VK_UP){
            player.jump(15);
        }else if (code == KeyEvent.VK_A) {
            player.getBackpack().toggle();
        }else if (code == KeyEvent.VK_S) {
            player.getBackpack().getCurrentItem().operate();
        }
        else if (code == KeyEvent.VK_Z) {
            isRunning = true;
        }
        else if (code == KeyEvent.VK_ESCAPE) {
            game.toggleMenu();
        }
        else if (code == KeyEvent.VK_H) {
            HighScoreReader reader = new HighScoreReader("data/scores.txt");
            try {
                reader.readScores();
                reader.sortByScore();
                reader.displayScores();
            }catch(IOException ee){
                ee.printStackTrace();
            }
        }
        else if (code == KeyEvent.VK_K) {
            try {
                GameSaverLoader.save("data/save.txt", game.currentLevel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
            else if (code == KeyEvent.VK_L) {
                try {
                    GameLevel loadedLevel = GameSaverLoader.load("data/save.txt", game);
                    game.setLevel(loadedLevel);

                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        else if (code == KeyEvent.VK_D) {
            player.dropBomb();
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            player.stopWalking();
        } else if (code == KeyEvent.VK_RIGHT) {
            player.stopWalking();
        } else if (code == KeyEvent.VK_Z) {
            isRunning = false;
        }
    }

    public void updatePlayer(Player newPlayer){
        player = newPlayer;
    }
    private float getSpeed() {
        return isRunning ? RUNNING_SPEED : WALKING_SPEED;
    }

}