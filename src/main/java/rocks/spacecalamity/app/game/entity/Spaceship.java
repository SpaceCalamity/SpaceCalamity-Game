package rocks.spacecalamity.app.game.entity;
import java.util.ArrayList;

public class Spaceship {
    private int hp;
    private int maxHp;
    private int speed;
    private int capacity;
    private Location loc;
    private ArrayList<Toggleable> controls;
    private ArrayList<GameObject> inventory;
    
    public Spaceship(int newHp, int newSpeed, int newCapacity, Location newLoc, ArrayList<Toggleable> newControls) {
        hp = newHp;
        speed = newSpeed;
        capacity = newCapacity;
        loc = newLoc;
        controls = newControls;
    }

    public boolean addToInventory(GameObject newItem) {
        if (inventory.size() < capacity) {
            inventory.add(newItem);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean dropFromInventory(GameObject dropped) {
        if (inventory.contains(dropped)) {
            inventory.remove(dropped);
            return true;
        }
        else{
            return false;
        }
    }

    public void restoreHp() {
        hp = maxHp;
    }

    /* need to implement storage aspect of spaceship */

}
