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
        this.hp = newHp;
        this.speed = newSpeed;
        this.capacity = newCapacity;
        this.loc = newLoc;
        this.controls = newControls;
    }

    public boolean addToInventory(GameObject newItem) {
        if (this.inventory.size() < capacity) {
            this.inventory.add(newItem);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean dropFromInventory(GameObject dropped) {
        if (this.inventory.contains(dropped)) {
            this.inventory.remove(dropped);
            return true;
        }
        else{
            return false;
        }
    }

    public void restoreHp() {
        this.hp = maxHp;
    }

    /* need to implement storage aspect of spaceship */

}
