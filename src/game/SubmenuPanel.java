package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SubmenuPanel extends JPanel{
    public JPanel MiniMenuPanel;
    private JButton saveButton;
    private JButton exitButton;
    private JButton howToPlayButton;
    private JButton settingsButton;
    private JButton restartButton;
    Game game;

    public SubmenuPanel(Game game) {
        this.game = game;

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameSaverLoader.save("data/save.txt", game.currentLevel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.view.transitionToSettings();
            }
        });
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MiniMenuPanel, "Instructions:\n- Use the arrow keys to move your character: \n  ← to move left \n  → to move right \n  ↓ to move down\n\n\n- Press 'Z' to run\n- Press 'A' to change item\n- Press 'S' to use item\n- Collect all the coins to win!", "How to Play", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    public JButton getSaveButton() {
        return saveButton;
    }

    public JPanel getMiniMenuPanel() {
        return MiniMenuPanel;
    }
}
