import Domain.BasketballMatch;
import Domain.Sports;
import Interface.MatchScorer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasketballMatchScorer implements MatchScorer {
    private static final Map<String, int[]> POSITION_POINTS = Map.of(
            "G", new int[]{2, 3, 1}, // Scored Points, Rebound, Assist
            "F", new int[]{2, 2, 2},
            "C", new int[]{2, 1, 3}
    );


    @Override
    public Map<String, Integer> calculateScore(List<? extends Sports> stats) {
        Map<String, Integer> sumScoredPoints = stats.stream().collect(Collectors.groupingBy(
                s -> s.getTeamName()
                , Collectors.summingInt(s -> ((BasketballMatch) s).getScoredPoints())
        ));
        String winningTeam = sumScoredPoints.entrySet()
                                            .stream().max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getKey).orElse(null);
        return stats.stream().collect(Collectors.toMap(
                s -> s.getPlayerName(),
                s ->s.getRatingPoint()+(s.getTeamName().equals(winningTeam) ? 10 : 0)
                ) );
    }
    @Override
    public Sports mapLineToSport(String line){
        try {

            String[] parts = line.split(";");
            if (parts.length < 8) {
                throw new IllegalArgumentException("Invalid Basketball line format.");
            }

            String name = parts[0];
            String nickname = parts[1];
            String teamName = parts[3];
            String position = parts[4];
            int num = Integer.parseInt(parts[2].trim());
            int scoredPoints = Integer.parseInt(parts[5].trim());
            int rebounds = Integer.parseInt(parts[6].trim());
            int assists = Integer.parseInt(parts[7].trim());

            int ratingPoints = calculateTotalRatingPoints(position, scoredPoints, rebounds, assists);

            return new BasketballMatch(name,nickname,num,teamName,position,scoredPoints,rebounds,assists,ratingPoints);
        } catch (Exception e) {
            System.err.println("Ignoring malformed Basketball line: " + line + ". Error: " + e.getMessage());
            return null;
        }
    }

    private int calculateTotalRatingPoints(String position, int scoredPoints, int rebounds, int assists) {
        int[] points = POSITION_POINTS.getOrDefault(position,new int[]{0, 0, 0});
        return scoredPoints*points[0] + rebounds*points[1] + assists*points[2];
    }


}
