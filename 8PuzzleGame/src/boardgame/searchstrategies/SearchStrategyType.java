package boardgame.searchstrategies;

public enum SearchStrategyType {
    A_STAR("A* search"),
    BEST_FIRST("Best-first search");

    private final String fullName;

    SearchStrategyType(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
