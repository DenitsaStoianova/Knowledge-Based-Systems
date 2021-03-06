package boardgame.heuristics;

public enum HeuristicType {
    MANHATTAN("Manhattan distance"),
    NUMBER_UNORDERED("Number of unordered tiles");

    private final String heuristicName;

    HeuristicType(String heuristicName){
        this.heuristicName = heuristicName;
    }

    public String getHeuristicName() {
        return heuristicName;
    }
}
