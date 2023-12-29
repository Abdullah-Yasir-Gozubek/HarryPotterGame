package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel {
    public JPanel MiniMenuPanel;
    private JCheckBox musicOffCheckBox;
    private JButton backButton;
    GameLevel currentLevel;
    Game game;


    public SettingsPanel(Game game) {
        this.game = game;


        musicOffCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (musicOffCheckBox.isSelected()) {
                    game.setMusicVolume(0.001f);  // Set the game music volume to 0f
                    if (currentLevel != null) {
                        currentLevel.getLevelMusic().stop();  // Stop the level music
                    }
                } else {
                    // Checkbox is not selected, restore the original music volume and restart the music
                    game.setMusicVolume(0.5f);  // Restore the game music volume
                    if (currentLevel != null) {
                        currentLevel.getLevelMusic().setVolume(0.5f);  // Restore the level music volume
                        currentLevel.getLevelMusic().play();// Restart the level music
                    }
                }
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.view.transitionToSub();
            }
        });
    }
}
