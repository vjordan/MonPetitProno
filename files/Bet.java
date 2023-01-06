package files;
public class Bet {
    
    private int team1;
    private int team2;
    private boolean bonus = false;

    public Bet (int team1, int team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Bet (int team1, int team2, boolean bonus) {
        this.team1 = team1;
        this.team2 = team2;
        this.bonus = bonus;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

}
