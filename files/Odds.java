package files;
public class Odds {
    
    private int team1;
    private int draw;
    private int team2;

    public Odds (int team1, int draw, int team2) {
        this.team1 = team1;
        this.draw = draw;
        this.team2 = team2;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }
    
}
