package files;
public class Goal {
    
    private int team;
    private int minute;
    private boolean firstHalf;
    
    public Goal (int team, int minute) {
        this.team = team;
        this.minute = minute;

        if (minute <= 45) {
            this.firstHalf = true;
        } else {
            this.firstHalf = false;
        }
    }

    public Goal (int team, int minute, boolean firstHalf) {
        this.team = team;
        this.minute = minute;
        this.firstHalf = firstHalf;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isFirstHalf() {
        return firstHalf;
    }

    public void setFirstHalf(boolean firstHalf) {
        this.firstHalf = firstHalf;
    }
    
}
