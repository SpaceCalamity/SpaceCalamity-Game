package rocks.spacecalamity.app.game.entity;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    private boolean isGameOver;
    
    public GameState(ArrayList<Player> newPlayers) {
        this.players = newPlayers;
        this.isGameOver = false;
    }

    private void play() {
        while(!this.isGameOver) {
            this.isGameOver = true;
        }
    }
}
