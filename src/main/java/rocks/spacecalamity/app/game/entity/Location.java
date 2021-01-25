package rocks.spacecalamity.app.game.entity;
import java.util.ArrayList;

public class Location {
    private String name;
    private ArrayList<Location> exits;
    private ArrayList<Toggleable> toggleables;
    
    public Location(String newName, ArrayList<Location> newExits) {
        name = newName;
        exits = newExits;
    }

    public void addExit(Location exit) {
        if (!exit.name.equals(name)) {
            exits.add(exit);
        }
    }    

    public boolean checkExit(Location exit) {
        for (int i = 0; i < exits.size(); i++) {
            if (exits.get(i).name.equals(exit.name)) {
                return true;
            } 
        }
        return false;
    }

    public ArrayList<Location> getExits() {
        return exits;
    }

    public boolean isHome() {
        return name.equals("Home");
    }

}
