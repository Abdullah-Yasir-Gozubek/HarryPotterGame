package game.BackpackItems;

import java.util.ArrayList;


//to keep the items in backpack created a backpack
public class Backpack {

    ArrayList<BackpackItem> items;
    private int currentItem;

    public Backpack(){
        items = new ArrayList<BackpackItem>();
        currentItem= -1;
    }

    public void addItem(BackpackItem item){
        items.add(item);
        currentItem = items.size()-1;
    }

    public BackpackItem getCurrentItem(){
        return items.get(currentItem);
    }

    public void toggle(){

        getCurrentItem().takeOff();
        currentItem++;
        if (currentItem == items.size())
            currentItem = 0;

        getCurrentItem().putOn();
        System.out.println("current item"+ getCurrentItem().getType());
    }
    public Broom getBroom() {
        for (BackpackItem item : items) {
            if (item instanceof Broom) {
                return (Broom) item;
            }
        }
        return null;
    }
    public Wand getWand() {
        for (BackpackItem item : items) {
            if (item instanceof Wand) {
                return (Wand) item;
            }
        }
        return null;
    }

}


