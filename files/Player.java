package files;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Comparable {
    
    private String name;
    private ArrayList<Bet> bets = new ArrayList<>();

    private int pointsDelta;

    public Player(String name) throws FileNotFoundException {
        this.name = name;
        fetchBets();
    }

    private void fetchBets() throws FileNotFoundException {
		
		File betsFile = new File("players/" + this.name + ".txt");
		Scanner betsScanner = new Scanner(betsFile);
		
		while (betsScanner.hasNextLine()) {
			String[] lineBets = betsScanner.nextLine().split(" ");
            int team1 = Integer.parseInt(lineBets[0]);
            int team2 = Integer.parseInt(lineBets[1]);
            if (lineBets.length == 2) {
                this.bets.add(new Bet(team1, team2));
            } else {
                this.bets.add(new Bet(team1, team2, true));
            }
        }
		
		betsScanner.close();
	}

    @Override
    public int compareTo(Object player) {
        return ((Player)player).getPointsDelta() - this.pointsDelta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public int getPointsDelta() {
        return pointsDelta;
    }

    public void setPointsDelta(int pointsDelta) {
        this.pointsDelta = pointsDelta;
    }

}
