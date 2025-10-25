import Domain.Sports;
import Interface.MatchScorer;

import java.util.*;

public class MVPCalculator {
    public static String findMVP(Map<String, Integer> totalPlayerRatings) {
        return totalPlayerRatings.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No MVP found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> lines = new HashMap<>();
        int counter = 0;
        StringBuilder key = new StringBuilder();
        String input;
        System.out.println("Enter text (type 'exit' to quit):");
        while (true) {
            input = sc.nextLine() + "\n";

            if ("exit".equalsIgnoreCase(input.trim())) {
                System.out.println("Exiting...");
                break;
            }
            if (!input.trim().isEmpty() && (SportType.HANDBALL.name().equals(input.trim()) || SportType.BASKETBALL.name().equals(input.trim()))) {
                counter++;
                key.delete(0, key.length());
                key.append(input.trim() + "_" + counter);
                continue;
            }

            lines.put(key.toString(), lines.get(key.toString()) == null ? "" + input : lines.get(key.toString()) + input);
        }

        sc.close();

        for (String k : lines.keySet()) {
            List<Sports> stats = new ArrayList<>();
            String matchStats = lines.get(k);
            String sport = k.split("_")[0].trim().toUpperCase();
            String arr[] = matchStats.split("\n");
            MatchScorer match = SportFactory.getMatchScorer(sport);
            for(int i = 0; i < arr.length; i++) {
                stats.add(match.mapLineToSport(arr[i]));
            }
            Map<String, Integer> result = match.calculateScore(stats);
            System.out.println("MVP PLAYER OF " + sport + " : " + findMVP(result));
        }
    }
}
