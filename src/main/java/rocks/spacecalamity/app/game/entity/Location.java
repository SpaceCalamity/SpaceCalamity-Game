package rocks.spacecalamity.app.game.entity;

import java.util.ArrayList;

public class Location {
    private String name;
    private ArrayList<Location> exits;
    private ArrayList<Toggleable> toggleables;
    
    public Location(String newName, ArrayList<Location> newExits) {
        this.name = newName;
        this.exits = newExits;
    }

    public void addExit(Location exit) {
        if (!exit.name.equals(this.name)) {
            this.exits.add(exit);
        }
    }    

    public boolean checkExit(Location exit) {
        for (int i = 0; i < this.exits.size(); i++) {
            if (this.exits.get(i).name.equals(exit.name)) {
                return true;
            } 
        }
        return false;
    }

    public ArrayList<Location> getExits() {
        return this.exits;
    }

    public boolean isHome() {
        return this.name.equals("Home");
    }

}
