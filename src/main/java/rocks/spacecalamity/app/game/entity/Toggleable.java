package rocks.spacecalamity.app.game.entity;
import java.util.ArrayList;

public class Toggleable {
    private ArrayList<State> states;
    private State s;

    public Toggleable(ArrayList<State> newStates) {
        states = newStates;
        s = states.get(0);
    }

    public State getNextState() {
        int currInd = states.indexOf(s);
        if (currInd == states.size() - 1) {
            return states.get(0);
        }
        else {
            return states.get(currInd + 1);
        }
    }

    /* Currently only allows for one way, one step toggling between states */
    public boolean toggle() {
        if (!s.getToggle()) {
            s = this.getNextState();
            return true;
        }
        else {
            return false;
        }
    }

    public void addState(State newS) {
        if (!states.contains(newS)) {
            states.add(newS);
        }
    }

    public void removeState(State removed) {
        if (states.contains(removed)) {
            states.remove(removed);
        }
    }

}
