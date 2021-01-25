package rocks.spacecalamity.app.game.entity;

public class State {
    private boolean canToggle;
    private String name;

    public State(boolean newCanToggle, String newName) {
        canToggle = newCanToggle;
        name = newName;
    }

    public boolean getToggle() {
        return canToggle;
    }

    public String getName() {
        return name;
    }
}
