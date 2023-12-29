package game;

import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerActionListener implements ActionListener {

    World world;

    public TimerActionListener(World w){
        world = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DynamicBody spawn = new DynamicBody(world, new CircleShape(1));
    }
}
