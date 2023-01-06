package files;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String koralplayPlayersList = "Alexandra Maxence Vincent Redouane Kim Victor Helene Franck Loic Fehizoro Frederic Francois Arnaud Perrine Romain Nadine";
        ArrayList<Player> koralplayPlayers = new ArrayList<>();
        for (String playerName : koralplayPlayersList.split(" ")) {
            koralplayPlayers.add(new Player(playerName));
        }
        League koralplayLeague = new League("Koralplay", koralplayPlayers);

        String filouPlayersList = "David Vincent Emeric Mael Thomas Augustin";
        ArrayList<Player> filouPlayers = new ArrayList<>();
        for (String playerName : filouPlayersList.split(" ")) {
            filouPlayers.add(new Player(playerName));
        }
        League filouLeague = new League("FILOU", filouPlayers);

        ArrayList<League> leagues = new ArrayList<>(Arrays.asList(koralplayLeague, filouLeague));
        ArrayList<Match> matches = fetchMatches();
        int minute = 85;
        computePointsDelta(leagues, matches, minute);
        for (League league : leagues) {
            System.out.println("Points gagnés/perdus après la " + minute + "ème minute pour les joueurs de la ligue " + league.getName() + " :");
            System.out.println(league.toString());
        }
    }

    private static void computePointsDelta(ArrayList<League> leagues, ArrayList<Match> matches, int minute) {

        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            int[] resultAtDefinedMinute = computeResultAtDefinedMinute(match, minute);
            for (League league : leagues) {
                for (Player player : league.getPlayers()) {
                    Bet bet = player.getBets().get(i);
                    if (bet.getTeam1() != -1) {
                        int potentialPoints = computePoints(match, bet, resultAtDefinedMinute[0], resultAtDefinedMinute[1]);
                        int gainedPoints = computePoints(match, bet, match.getResultTeam1(), match.getResultTeam2());
                        player.setPointsDelta(player.getPointsDelta() + (gainedPoints - potentialPoints));
                    }
                }
            }
        }
    }

    private static int[] computeResultAtDefinedMinute(Match match, int minute) {

        int[] result = new int[2];
        result[0] = 0;
        result[1] = 0;

        for (Goal goal : match.getGoals()) {
            if ((minute > 45 && goal.isFirstHalf()) || goal.getMinute() <= minute) {
                if (goal.getTeam() == 1) {
                    result[0]++;
                } else {
                    result[1]++;
                }
            }
        }

        return result;
    }

    private static int computePoints(Match match, Bet bet, int team1, int team2) {

        int points = 0;

        int betTeam1 = bet.getTeam1();
        int betTeam2 = bet.getTeam2();
        if (team1 > team2 && betTeam1 > betTeam2) {
            points = match.getOdds().getTeam1();
            if (team1 == betTeam1 && team2 == betTeam2) {
                points *= 2;
            }
        } else if (team2 > team1 && betTeam2 > betTeam1) {
            points = match.getOdds().getTeam2();
            if (team1 == betTeam1 && team2 == betTeam2) {
                points *= 2;
            }
        } else if (team1 == team2 && betTeam1 == betTeam2) {
            points = match.getOdds().getDraw();
            if (team1 == betTeam1) {
                points *= 2;
            }
        }
        if (bet.isBonus()) {
            points *= 2;
        }

        return points;
    }

    private static ArrayList<Match> fetchMatches() throws FileNotFoundException {
		
		ArrayList<Match> matches = new ArrayList<>();
		
		File matchesFile = new File("files/Matches.txt");
		Scanner matchesScanner = new Scanner(matchesFile);
		
		while (matchesScanner.hasNextLine()) {
			String[] lineFile = matchesScanner.nextLine().split(":");
            String[] lineNameAndOdds = lineFile[0].split(" ");
            String name = lineNameAndOdds[0] + " " + lineNameAndOdds[1];
            Odds odds = new Odds(Integer.parseInt(lineNameAndOdds[2]), Integer.parseInt(lineNameAndOdds[3]), Integer.parseInt(lineNameAndOdds[4]));
            ArrayList<Goal> goals = new ArrayList<>();
            if (lineFile.length == 2) {
                String[] lineGoals = lineFile[1].split(" ");
                for (int i = 0; i < lineGoals.length-1; i+=2) {
                    int team = Integer.parseInt(lineGoals[i+1]);
                    String lineMinute = lineGoals[i];
                    if (lineMinute.contains("+")) {
                        String[] lineMinuteWithAdditionnal = lineMinute.split("\\+");
                        int minute = Integer.parseInt(lineMinuteWithAdditionnal[0]) + Integer.parseInt(lineMinuteWithAdditionnal[1]);
                        goals.add(new Goal(team, minute, true));
                    } else {
                        int minute = Integer.parseInt(lineMinute);
                        goals.add(new Goal(team, minute));
                    }
                }
            }
            matches.add(new Match(name, odds, goals));
        }
		
		matchesScanner.close();
		
		return matches;
	}

}

