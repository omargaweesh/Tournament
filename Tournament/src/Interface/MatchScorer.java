package Interface;

import Domain.Sports;

import java.util.List;
import java.util.Map;

public interface MatchScorer {
    Map<String , Integer> calculateScore(List<? extends Sports> stats);
    Sports mapLineToSport(String line);
}
