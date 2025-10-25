package Domain;

public class BasketballMatch extends Sports {
    private final int scoredPoints;
    private final int rebounds;
    private final int assists;


    public BasketballMatch(String playerName, String nickName, int number, String teamName, String position
                                    , int scoredPoints, int rebounds, int assists , int totalRatingPoint) {
        super(playerName, nickName, number, teamName, position ,totalRatingPoint);

        this.scoredPoints = scoredPoints;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

}
