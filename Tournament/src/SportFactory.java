import Interface.MatchScorer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SportFactory {
    private static final Map<String, MatchScorer> strategies = new HashMap<>();

    static {
        strategies.put(SportType.BASKETBALL.name(), new BasketballMatchScorer());
        strategies.put(SportType.HANDBALL.name(), new HandballMatchScorer());
    }



    public static MatchScorer getMatchScorer(String sportType) {
        MatchScorer match = strategies.get(sportType);

        if (Objects.isNull(match))
            throw new IllegalArgumentException("Sport not found");

        return match;
    }
}
