package rocks.spacecalamity.app.game.entity;

public class State {
    private boolean canToggle;
    private String name;

    public State(boolean newCanToggle, String newName) {
        this.canToggle = newCanToggle;
        this.name = newName;
    }

    public boolean getToggle() {
        return this.canToggle;
    }

    public String getName() {
        return this.name;
    }

    public void changeToggle() {
        this.canToggle = !this.canToggle;
    }
}
