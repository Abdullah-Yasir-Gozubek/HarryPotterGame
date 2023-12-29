package game;

import game.Players.Player;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends UserView implements ActionListener {

    private Image[] backgrounds;
    private Image coinImage;
    private Game game;
    private int[] x;
    private int thirdImageX;
    GameLevel currentLevel;
//    private JButton quitButton;
    private SubmenuPanel submenuPanel;
    private SettingsPanel settingsPanel;
    private SubIconPanel subIconPanel;



    public GameView(GameLevel w, Game game, int width, int height) {
        super(w, width, height);
        this.game = game;
        currentLevel = w;
        coinImage =  new ImageIcon("data/coins.png").getImage();
        backgrounds = new Image[3];
//        backgrounds[0] = new ImageIcon("data/1.png").getImage();
//        backgrounds[1] = new ImageIcon("data/2.png").getImage();
//        backgrounds[2] = new ImageIcon("data/3.png").getImage();

        x = new int[backgrounds.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
        }
        thirdImageX = 0;


        subIconPanel = new SubIconPanel(game);
        add(subIconPanel.MiniMenuPanel);

        submenuPanel = new SubmenuPanel(game);
        add(submenuPanel.MiniMenuPanel);

        settingsPanel = new SettingsPanel(game);
        add(settingsPanel.MiniMenuPanel);


        subIconPanel.MiniMenuPanel.setVisible(true);
        submenuPanel.MiniMenuPanel.setVisible(false);
        settingsPanel.MiniMenuPanel.setVisible(false);

    }


    public void toggleSubmenuPanel() {
        if (submenuPanel.MiniMenuPanel.isVisible()) {
            submenuPanel.MiniMenuPanel.setVisible(false);
        } else {
            submenuPanel.MiniMenuPanel.setVisible(true);
        }
    }

    @Override
    public void setWorld(World w){
        super.setWorld(w);
        currentLevel = (GameLevel)w;
    }


    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(currentLevel.getBackgrounds()[0], 0, 0, getWidth(), getHeight(), this);
        g.drawImage(currentLevel.getBackgrounds()[1], x[1], 0, getWidth(), getHeight(), this);
        g.drawImage(currentLevel.getBackgrounds()[1], x[1] + getWidth(), 0, getWidth(), getHeight(), this);
        g.drawImage(currentLevel.getBackgrounds()[2], thirdImageX, 30, getWidth(), getHeight(), this);

        x[1] -= 1;
        x[2] -= 1;

        if (x[1] <= -getWidth()) {
            x[1] = x[2] + getWidth();
        }
        if (x[2] <= -getWidth()) {
            x[2] = x[1] + getWidth();
        }
    }


    @Override
    protected void paintForeground(Graphics2D g) {
       super.paintForeground(g);
        if (game.isGameOver()){
            g.drawString("Game Over", this.getWidth()/2, this.getHeight()/2);
        }

        g.setFont(new Font("TimesRoman", Font.BOLD, 23));
        g.setPaint(Color.white);
        g.drawImage(coinImage, getWidth() - 105, 8, 50, 50, null);
        g.drawString(": " + game.getCoinsCount(), getWidth() - 45, 42);
        int health = game.getPlayer().getHealth();
        int numHearts = Math.max(0, Math.min((health / 101) + 1, (health > 0 ? 3 : 0)));

        if (health >= 301 && health < 401) {
            numHearts = 4;
        } else if (health >= 401) {
            numHearts = 5;
        }
        for (int i = 0; i < numHearts; i++) {
            g.drawImage(new ImageIcon("data/heart.png").getImage(), 20 + i * 30, 20, 30, 30, null);


        }
    }


    public void transitionToSub() {
        settingsPanel.MiniMenuPanel.setVisible(false);
        submenuPanel.MiniMenuPanel.setVisible(true);
    }

    public void transitionToSettings() {
        submenuPanel.MiniMenuPanel.setVisible(false);
        settingsPanel.MiniMenuPanel.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("run");
    }
}


