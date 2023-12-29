package game;

import city.cs.engine.DynamicBody;
import game.Coins.Coins;
import game.Enemies.Enemy;
import game.Levels.Level1;
import game.Levels.Level2;
import game.Levels.Level3;

import game.Players.Player;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {

    public  static void save(String fileName, GameLevel level)
    throws IOException {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);


            writer.write(level.getName() + "\n");
            for (int i=0; i<level.getDynamicBodies().size(); i++){
                DynamicBody b =level.getDynamicBodies().get(i);

                if (b instanceof Coins){
                    writer.write("coins" + b.getPosition().x + "," + b.getPosition().y + "\t");
                }
                else if (b instanceof Player){
                    writer.write("player" + b.getPosition().x + "," + b.getPosition().y + "," + ((Player) b).getCoinCount() + "\t");
                }
                else if (b instanceof Enemy){
                    writer.write("enemy" + b.getPosition().x + "," + b.getPosition().y + "\t");
                }
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    public static GameLevel load(String fileName, Game game) throws  IOException{
        FileReader fr = null;
        BufferedReader reader = null;


        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);

            String line = reader.readLine();
            GameLevel level = null;
            if (line.equals("Level1")){
                level = new Level1(game);
            } else if (line.equals("Level2")){
                level = new Level2(game);
            } else if (line.equals("Level3")){
                level = new Level3(game);
            }


            line = reader.readLine();
            while (line != null) {

                String[] tokens = line.split(",");

                if(tokens[0].equals("Coins")){
                    Coins coins = new Coins(level);
                    coins.setPosition(new Vec2(Float.parseFloat(tokens[1]),Float.parseFloat(tokens[2])));
                }
                else if(tokens[0].equals("Enemy")){
                    Enemy enemy = new Enemy(level);
                    enemy.setPosition(new Vec2(Float.parseFloat(tokens[1]),Float.parseFloat(tokens[2])));
                }
                else if(tokens[0].equals("Player")){
                    Player player = new Player(level);
                    player.setPosition(new Vec2(Float.parseFloat(tokens[1]),Float.parseFloat(tokens[2])));
                    player.setCoinCount(Integer.parseInt(tokens[3]));
                }

//                String name = tokens[0];
//                int score = Integer.parseInt(tokens[1]);
//                System.out.println("Name: " + name + ", Score: " + score);
                line = reader.readLine();
            }
                return level;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
