package game.Others;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.GameView;
import game.Players.Player;

public class Tracker implements StepListener {
    private GameView view;
    private Player player;


    public Tracker(GameView view, Player player) {
        this.view = view;
        this.player = player;

    }

    public void preStep(StepEvent e) {}

    public void postStep(StepEvent e) {
        view.setCentre(player.getPosition());

    }
}





