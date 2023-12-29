package game.BackpackItems;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import game.Players.Player;
import org.jbox2d.common.Vec2;

public abstract class BackpackItem {


    protected Player player;
    protected BodyImage image;
    protected AttachedImage aImage;
    public BackpackItem(Player player){
        this.player= player;
    }

    public void putOn(){
        aImage = player.addImage(image);
        aImage.setOffset(new Vec2(-1.8f,0));


        if (player.getDirection().equals("left"))
            aImage.flipHorizontal();

    }

    public void takeOff() {
        player.removeAttachedImage(aImage);
    }

    public abstract String getType();

    public abstract void operate();
}
