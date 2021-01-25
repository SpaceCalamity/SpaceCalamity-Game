package rocks.spacecalamity.app.game.entity;

public class Player {
    private String firstName;
    private String lastName;
    private int hp;
    private int bankBalance;
    private String consoleMsg;
    private Spaceship ship;
    private Location loc;

    public Player(String first, String last) {
        firstName = first;
        lastName = last;
        hp = 100;
        bankBalance = 10;
    }

    public String getName() {
        return firstName + lastName;
    }

    public void setName(String newFirst, String newLast) {
        firstName = newFirst;
        lastName = newLast; 
    }

    public void restoreHp(int moreHp) {
        hp += moreHp;
    }
    
    public void deposit(int d) {
        bankBalance += d;
    }

    public void withdraw(int d) {
        if (bankBalance >= d) {
            bankBalance -= d;
        }
        else {
            consoleMsg = "Insufficient balance.";
        }
    }   

    public boolean goNewLoc(Location newLoc) {
        if (loc.getExits().contains(newLoc)) {
            loc = newLoc;
            return true;
        }
        else {
            return false;
        }
    }

    public void resetCMSG() {
        consoleMsg = "";
    }

    public String speak(String t) {
        return t;
    }


}
