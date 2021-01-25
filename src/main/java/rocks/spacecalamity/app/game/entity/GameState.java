package rocks.spacecalamity.app.game.entity;
import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    private boolean isGameOver;
    
    public GameState(ArrayList<Player> newPlayers) {
        players = newPlayers;
        isGameOver = false;
    }

    private void play() {
        while(!isGameOver) {
            isGameOver = true;
        }
    }
}
