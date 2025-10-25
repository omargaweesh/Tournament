import Domain.HandballMatch;
import Domain.Sports;
import Interface.MatchScorer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandballMatchScorer implements MatchScorer {
    private static final Map<String, int[]> POSITION_POINTS = Map.of(
            "G", new int[]{50, 5, -2},
            "F", new int[]{20, 1, -1}
    );


    @Override
    public Map<String, Integer> calculateScore(List<? extends Sports> stats) {
        Map<String, Integer> sumScoredPoints = stats.stream().collect(Collectors.groupingBy(
                s -> s.getTeamName()
                , Collectors.summingInt(s -> ((HandballMatch) s).getGoalsMade())
        ));
        String winningTeam = sumScoredPoints.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);

        return stats.stream().collect(Collectors.toMap(
                s -> s.getPlayerName(),
                s -> s.getRatingPoint() + (s.getTeamName().equals(winningTeam) ? 10 : 0)
        ));
    }
    @Override
    public Sports mapLineToSport(String line) {
        try {

            String[] parts = line.split(";");
            if (parts.length < 7) {
                throw new IllegalArgumentException("Invalid HandBall line format.");
            }

            String name = parts[0];
            String nickname = parts[1];
            String teamName = parts[3];
            String position = parts[4];
            int num = Integer.parseInt(parts[2].trim());
            int goalMade = Integer.parseInt(parts[5].trim());
            int goalReceived = Integer.parseInt(parts[6].trim());

            int ratingPoints = calculateTotalRatingPoints(position, goalMade, goalReceived);

            return new HandballMatch(name, nickname, num, teamName, position, goalMade, goalReceived, ratingPoints);
        } catch (Exception e) {
            System.err.println("Ignoring malformed Basketball line: " + line + ". Error: " + e.getMessage());
            return null;
        }
    }

    private int calculateTotalRatingPoints(String position, int goalMade, int goalReceived) {
        int[] points = POSITION_POINTS.getOrDefault(position, new int[]{0, 0, 0});
        return points[0] + goalMade * points[1] + goalReceived * points[2];
    }


}
