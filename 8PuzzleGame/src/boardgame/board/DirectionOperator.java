package boardgame.board;

public enum DirectionOperator {
    LEFT("Left"),
    RIGHT("Right"),
    UP("Up"),
    DOWN("Down"),
    START("Start");

    private String directionName;

    private DirectionOperator(String directionName){
        this.directionName = directionName;
    }

    public String getDirectionName() {
        return directionName;
    }
}
