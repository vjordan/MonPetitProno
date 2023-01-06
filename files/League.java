package files;
import java.util.ArrayList;
import java.util.Collections;

public class League {
    
    private String name;
    private ArrayList<Player> players;
    
    public League(String name, ArrayList<Player> players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String toString() {

        String ranking = "";

        Collections.sort(this.players);
        for (Player player : this.players) {
            int pointsDelta = player.getPointsDelta();
            ranking += player.getName() + " : " + (pointsDelta >= 0 ? "+" : "") + pointsDelta + "\n";
        }

        return ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
