package game;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

class ScoreRecord{
    String name;
    int score;
    int minutes;
    int seconds;

    public ScoreRecord(String name, int score, int minutes, int seconds){
        this.name = name;
        this.score = score;
        this.minutes = minutes;
        this.seconds = seconds;
    }
}



public class HighScoreReader {

    private String file;
    private ArrayList<ScoreRecord> scores;

    public HighScoreReader(String file) {
        this.file = file;
        scores = new ArrayList<>();
    }


    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + file + " ...");
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);

                String[] timeTokens = tokens[2].split(":");
                int minutes = Integer.parseInt(timeTokens[0]);
                int seconds = Integer.parseInt(timeTokens[1]);

                ScoreRecord scoreRecord = new ScoreRecord(name, score, minutes, seconds);
                scores.add(scoreRecord);

                System.out.println("Name: " + name + ", Score: " + score);
                line = reader.readLine();
            }
            System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public void displayScores(){
        System.out.println("Player\tScore\tTime");
        for (int i=0; i<scores.size(); i++){
            ScoreRecord score = scores.get(i);
            System.out.println(score.name +"\t"+ score.score +"\t"+ score.minutes + ":" + score.seconds);
        }
    }

    public void sortByScore(){
        scores.sort(new Comparator<ScoreRecord>() {
            @Override
            public int compare(ScoreRecord o1, ScoreRecord o2) {
                return o2.score - o1.score;
            }
        });
    }
    public void sortByTime(){
        scores.sort(new Comparator<ScoreRecord>() {
            @Override
            public int compare(ScoreRecord o1, ScoreRecord o2) {
                int sec1 = o1.minutes*60 + o1.seconds;
                int sec2 = o2.minutes*60 + o2.seconds;
                return sec1 - sec2;
            }
        });
    }


    public static void main(String[] args) throws IOException {
        HighScoreReader demo = new HighScoreReader(args[0]);
        demo.readScores();
    }
}