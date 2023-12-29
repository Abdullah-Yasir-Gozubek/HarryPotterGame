package game.Enemies;

import city.cs.engine.Shape;
import city.cs.engine.*;
import game.Game;
import game.GameLevel;
import game.Players.Player;

import java.awt.*;

public class TrapBox extends StaticBody implements SensorListener, StepListener {

//    private static final Shape boxShape = new BoxShape(3.5f,1f);
   private static final BodyImage boxImage =
           new BodyImage("data/trapbox.png", 2.0f);
    private Sensor boxSensor;
    //private GhostlyFixture boxView;
    private Player player;
    private boolean inTrap;
    private Game game;


    public TrapBox(GameLevel w, Game game) {
        super(w);
        this.game = game;
        addImage(boxImage);
        Shape boxShape = new BoxShape(3.5f, 0.5f);
        Fixture boxFixture = new SolidFixture(this, boxShape);
        boxSensor = new Sensor(this, boxShape);
       boxSensor.addSensorListener(this);
       // boxView = new GhostlyFixture(this, boxShape);
        this.setFillColor(Color.CYAN);
        this.player = w.getPlayer();
        this.player.setHealth(300);
        this.inTrap = false;
        w.addStepListener(this);
    }



    @Override
    public void endContact(SensorEvent sensorEvent) {
        inTrap = false;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Player) {
            inTrap = true;
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (inTrap && player.getHealth() > 0){
             player.setHealth(player.getHealth()-1);
            if (player.getHealth() <= 0) {
                game.setGameOver(true);

            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
