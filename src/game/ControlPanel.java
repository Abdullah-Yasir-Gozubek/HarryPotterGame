package game;

import city.cs.engine.SoundClip;
import game.Levels.Level1;
import game.Levels.Level2;
import game.Levels.Level3;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    public JPanel mainPanel;
    private JButton playButton;
    private JButton levelsButton;
    private JButton quitButton;
    private JButton howToPlayButton;
    private JButton scoresButton;
    private JButton settingsButton;
    private SoundClip currentMusic;
    GameLevel currentLevel;

    private Game game;

    public ControlPanel(Game game) {
        this.game = game;


        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("data/MenuBackground.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };

        // Create the main menu panel
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setPreferredSize(new Dimension(800, 600));

        // Set constraints for the title label
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;


        ImageIcon icon = new ImageIcon("data/HarryPotterLogo.png");
        Image img = icon.getImage().getScaledInstance(350, 150, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(img);
        JLabel titleLabel = new JLabel("", smallIcon, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        menuPanel.add(titleLabel, c);








        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;



        playButton = new JButton("Play");
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Arial", Font.BOLD, 25));
        playButton.setPreferredSize(new Dimension(150, 50));
 //      playButton.setOpaque(false);  // make the button background transparent
        playButton.setContentAreaFilled(false); // make the button content area transparent
        playButton.setBorderPainted(false); // remove the button border
        ImageIcon playIcon = new ImageIcon("data/Buttons/PlayIcon.png");
        Image playImage = playIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedPlayIcon = new ImageIcon(playImage);
        playButton.setIcon(resizedPlayIcon);



        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the game when the play button is clicked
                startGame();
            }
        });
        menuPanel.add(playButton, c);



        //menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));



        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;


        levelsButton = new JButton("Levels");
        levelsButton.setForeground(Color.WHITE);
        levelsButton.setPreferredSize(new Dimension(250, 50));
        levelsButton.setFont(new Font("Arial", Font.BOLD, 25));
        levelsButton.setContentAreaFilled(false); // make the button content area transparent
        levelsButton.setBorderPainted(false); // remove the button border
        ImageIcon levelIcon = new ImageIcon("data/Buttons/LevelsIcon.png");
        Image levelImage = levelIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedLevelIcon = new ImageIcon(levelImage);
        levelsButton.setIcon(resizedLevelIcon);

        levelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.removeAll(); // clear the panel
                menuPanel.setLayout(new GridBagLayout()); // set the layout again

                // create constraints for the buttons
                GridBagConstraints buttonConstraints = new GridBagConstraints();
                buttonConstraints.insets = new Insets(10, 10, 10, 10);
                buttonConstraints.gridx = 0;
                buttonConstraints.gridy = 0;
                buttonConstraints.anchor = GridBagConstraints.CENTER;

                // add the level buttons
                for (int i = 1; i <= 3; i++) {
                    JButton levelButton = new JButton("Level " + i);
                    levelButton.setPreferredSize(new Dimension(150, 50));

                    levelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String buttonText = ((JButton) e.getSource()).getText();
                            int level = Integer.parseInt(buttonText.split(" ")[1]);
                            // The level variable should now contain the correct level number (1, 2, or 3).

                            switch (level) {
                                case 1:
                                    currentLevel = new Level1(game);
                                    break;
                                case 2:
                                    currentLevel = new Level2(game);
                                    break;
                                case 3:
                                    currentLevel = new Level3(game);
                                    break;
                                default:
                                    break;
                            }

                            if (game.currentLevel.getLevelMusic() != null) {
                                game.currentLevel.getLevelMusic().stop();
                            }
                            startGame(); // This method should start the game with the currentLevel.
                            if (game.currentLevel.getLevelMusic() != null) {
                                game.currentLevel.getLevelMusic().loop();
                                game.currentLevel.populate();
                                game.view.setWorld(currentLevel);
                                game.controller.updatePlayer(currentLevel.getPlayer());
                                game.currentLevel.start();


                            }
                        }
                    });

                    menuPanel.add(levelButton, buttonConstraints);

                    buttonConstraints.gridx++; // move to the next column
                }

                JButton backButton = new JButton("Back");
                backButton.setPreferredSize(new Dimension(150, 50));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuPanel.removeAll(); // clear the panel
                        menuPanel.setLayout(new GridBagLayout()); // set the layout again
                       // menuPanel(); // call the method to setup the main menu
                        menuPanel.revalidate(); // revalidate the panel to update the changes
                        menuPanel.repaint(); // repaint the panel to reflect the changes
                    }
                });
                buttonConstraints.gridx = 0; // reset the column to the first one
                buttonConstraints.gridy++; // move to the next row
                buttonConstraints.gridwidth = 4; // set the button to span across all columns
                menuPanel.add(backButton, buttonConstraints);

                menuPanel.revalidate(); // revalidate the panel to update the changes
                menuPanel.repaint(); // repaint the panel to reflect the changes
            }
        });

        menuPanel.add(levelsButton, c);


        // Set constraints for the howToPlay button
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        settingsButton = new JButton("Settings");
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setPreferredSize(new Dimension(250, 50));
        settingsButton.setFont(new Font("Arial", Font.BOLD, 25));
        settingsButton.setContentAreaFilled(false); // make the button content area transparent
        settingsButton.setBorderPainted(false); // remove the button border
        ImageIcon settingIcon = new ImageIcon("data/Buttons/SettingsIcon.png");
        Image settingImage = settingIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedSettingIcon = new ImageIcon(settingImage);
        settingsButton.setIcon(resizedSettingIcon);



        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JPanel to hold the components
                JPanel settingsPanel = new JPanel();
                settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));

                // Create a label for the music volume
                JLabel musicVolumeLabel = new JLabel("Music Volume:");
                settingsPanel.add(musicVolumeLabel);

                // Create a slider for the music volume
                JSlider musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
                musicVolumeSlider.setMajorTickSpacing(25);
                musicVolumeSlider.setMinorTickSpacing(5);
                musicVolumeSlider.setPaintTicks(true);
                musicVolumeSlider.setPaintLabels(true);

                musicVolumeSlider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int volume = musicVolumeSlider.getValue();
                        float volumeFloat = volume / 100f;
                        game.setMusicVolume(volumeFloat);
                        if (currentLevel != null) {
                            currentLevel.getLevelMusic().setVolume(volumeFloat);
                        }
                    }

                });

                settingsPanel.add(musicVolumeSlider);


                // Display the settings dialog with the new panel
                JOptionPane.showMessageDialog(mainPanel, settingsPanel, "Settings", JOptionPane.PLAIN_MESSAGE);
            }
        });

        menuPanel.add(settingsButton, c);


      //  menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));




        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        scoresButton = new JButton("Scores");
        scoresButton.setForeground(Color.WHITE);
        scoresButton.setFont(new Font("Arial", Font.BOLD, 25));
        scoresButton.setPreferredSize(new Dimension(250, 50));
        scoresButton.setContentAreaFilled(false); // make the button content area transparent
        scoresButton.setBorderPainted(false); // remove the button border
        ImageIcon scoreIcon = new ImageIcon("data/Buttons/ScoresIcon.png");
        Image scoreImage = scoreIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedScoreIcon = new ImageIcon(scoreImage);
        scoresButton.setIcon(resizedScoreIcon);


        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display a message dialog with high scores
                JOptionPane.showMessageDialog(mainPanel, "High Scores:\n1. Player 1 - 1000 points\n2. Player 2 - 800 points\n3. Player 3 - 600 points", "High Scores", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuPanel.add(scoresButton, c);


       // menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));



        howToPlayButton = new JButton("How to Play");
        howToPlayButton.setForeground(Color.WHITE);
        howToPlayButton.setFont(new Font("Arial", Font.BOLD, 25));
        howToPlayButton.setContentAreaFilled(false); // make the button content area transparent
        howToPlayButton.setBorderPainted(false); // remove the button border
        howToPlayButton.setMargin(new Insets(5, 15, 5, 50));
        ImageIcon howtoIcon = new ImageIcon("data/Buttons/HowToPlayIcon.png");
        Image howtoImage = howtoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedHowtoIcon = new ImageIcon(howtoImage);
        howToPlayButton.setIcon(resizedHowtoIcon);


        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display a message dialog with game rules
                JOptionPane.showMessageDialog(mainPanel, "Instructions:\n- Use the arrow keys to move your character: \n  ← to move left \n  → to move right \n  ↓ to move down\n\n\n- Press 'Z' to run\n- Press 'A' to change item\n- Press 'S' to use item\n- Press 'D' to drop the magic ball\n- Press 'ESC' to pop up Sub Menu\n- Kill all the enemies to win!" , "How to Play", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(howToPlayButton);



        menuPanel.add(Box.createRigidArea(new Dimension(390, 150)));



        quitButton = new JButton("Quit");
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Arial", Font.BOLD, 25));
        quitButton.setContentAreaFilled(false); // make the button content area transparent
        quitButton.setBorderPainted(false); // remove the button border
        quitButton.setMargin(new Insets(5, 0, 5, 15));
        ImageIcon QuitIcon = new ImageIcon("data/Buttons/QuitIcon.png");
        Image QuitImage = QuitIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedQuitIcon = new ImageIcon(QuitImage);
        quitButton.setIcon(resizedQuitIcon);


        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quit the game when the quit button is clicked
                System.exit(0);
            }
        });
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(quitButton);

        // Create the control panel
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(menuPanel, BorderLayout.CENTER);

        // Set the main panel to the control panel
        mainPanel = controlPanel;





        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change the button's background color when the mouse enters
                playButton.setBackground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Change the button's background color back to the original color when the mouse exits
                playButton.setBackground(UIManager.getColor("control"));
            }
        });


        howToPlayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change the button's background color when the mouse enters
                howToPlayButton.setBackground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Change the button's background color back to the original color when the mouse exits
                howToPlayButton.setBackground(UIManager.getColor("control"));
            }
        });

        scoresButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change the button's background color when the mouse enters
                scoresButton.setBackground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Change the button's background color back to the original color when the mouse exits
                scoresButton.setBackground(UIManager.getColor("control"));
            }
        });

        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change the button's background color when the mouse enters
                settingsButton.setBackground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Change the button's background color back to the original color when the mouse exits
                settingsButton.setBackground(UIManager.getColor("control"));
            }
        });

        quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change the button's background color when the mouse enters
                quitButton.setBackground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Change the button's background color back to the original color when the mouse exits
                quitButton.setBackground(UIManager.getColor("control"));
            }
        });


    }



    private void startGame() {
        // Create the game panel and switch to it
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(game.view, BorderLayout.CENTER);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel);
        frame.pack();

        // Start the game
        game.currentLevel.start();
        game.view.requestFocusInWindow();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
