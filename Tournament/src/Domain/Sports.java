package Domain;

import java.util.Objects;

public abstract class Sports {
    private final String playerName;
    private final String nickName;
    private final int number;
    private final String teamName;
    private final String position;
    private final int totalRatingPoint;

    Sports(String playerName, String nickName, int number, String teamName, String position, int totalRatingPoint) {
        this.playerName = playerName;
        this.nickName = nickName;
        this.number = number;
        this.teamName = teamName;
        this.position = position;
        this.totalRatingPoint = totalRatingPoint;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getRatingPoint() {
        return totalRatingPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sports sports = (Sports) o;
        return Objects.equals(getPlayerName(), sports.getPlayerName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPlayerName());
    }

  /*  public abstract int getTotalRatingPoint() ;*/
}
