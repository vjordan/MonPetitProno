package files;
import java.util.ArrayList;

public class Match {
    
    private String name;
    private Odds odds;
    private ArrayList<Goal> goals;

    private int resultTeam1;
    private int resultTeam2;

    public Match(String name, Odds odds, ArrayList<Goal> goals) {
        this.name = name;
        this.odds = odds;
        this.goals = goals;
        computeResult();
    }

    private void computeResult() {

        this.resultTeam1 = 0;
        this.resultTeam2 = 0;
        for (Goal goal : this.goals) {
            if (goal.getTeam() == 1) {
                this.resultTeam1++;
            } else {
                this.resultTeam2++;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    public int getResultTeam1() {
        return resultTeam1;
    }

    public void setResultTeam1(int resultTeam1) {
        this.resultTeam1 = resultTeam1;
    }

    public int getResultTeam2() {
        return resultTeam2;
    }

    public void setResultTeam2(int resultTeam2) {
        this.resultTeam2 = resultTeam2;
    }

}
