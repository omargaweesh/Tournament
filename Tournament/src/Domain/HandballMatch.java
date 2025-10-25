package Domain;

public class HandballMatch extends Sports {
    private final int goalsMade;
    private final int goalsReceived;

    public HandballMatch(String playerName, String nickName, int number, String teamName
                                 , String position, int goalsMade, int goalsReceived, int totalRatingPoint) {
        super(playerName, nickName, number, teamName, position,totalRatingPoint);
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }

    public int getGoalsMade() {
        return goalsMade;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

/*
    @Override
    public int getTotalRatingPoint() {
        return getTotalRatingPoint();
    }*/
}
