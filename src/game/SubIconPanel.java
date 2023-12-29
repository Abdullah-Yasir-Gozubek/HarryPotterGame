package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubIconPanel {
    public JPanel MiniMenuPanel;
    private JButton setButton;
    private JButton restartButton;

    Game game;
    public SubIconPanel(Game game) {
        this.game = game;



        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restartCurrentLevel();
            }
        });
    }

}
