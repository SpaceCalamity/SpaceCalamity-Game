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
        this.firstName = first;
        this.lastName = last;
        this.hp = 100;
        this.bankBalance = 10;
    }

    public String getName() {
        return this.firstName + this.lastName;
    }

    public void setName(String newFirst, String newLast) {
        this.firstName = newFirst;
        this.lastName = newLast; 
    }

    public void restoreHp(int moreHp) {
        this.hp += moreHp;
    }
    
    public void deposit(int d) {
        this.bankBalance += d;
    }

    public void withdraw(int d) {
        if (this.bankBalance >= d) {
            this.bankBalance -= d;
        }
        else {
            this.consoleMsg = "Insufficient balance.";
        }
    }   

    public boolean goNewLoc(Location newLoc) {
        if (this.loc.getExits().contains(newLoc)) {
            this.loc = newLoc;
            return true;
        }
        else {
            return false;
        }
    }

    public void resetCMSG() {
        this.consoleMsg = "";
    }

}
