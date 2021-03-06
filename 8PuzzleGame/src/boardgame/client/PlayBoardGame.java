package boardgame.client;

import boardgame.board.BoardState;
import boardgame.board.DirectionOperator;
import boardgame.exceptions.WrongStrategyException;
import boardgame.heuristics.HeuristicType;
import boardgame.node.ANode;
import boardgame.node.Node;
import boardgame.searchstrategies.AStarSearch;
import boardgame.searchstrategies.BestFirstSearch;
import boardgame.searchstrategies.SearchStrategy;
import boardgame.node.AbstractNode;
import boardgame.searchstrategies.SearchStrategyType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayBoardGame {
    private static final List<DirectionOperator> operatorList = new ArrayList<>();

    public static void printStatesToGoal(AbstractNode goalState) {
        if (goalState.getParent() == null) {
            System.out.println(goalState);
            //System.out.println(goalState.getNodeConfiguration());
            return;
        }
        System.out.println(goalState);
        //System.out.println(goalState.getNodeConfiguration());
        printStatesToGoal(goalState.getParent());
    }

    public static void findDirectionsToGoal(AbstractNode goalState) {
        if (goalState.getParent() == null) {
            operatorList.add(goalState.getOperator());
            return;
        }
        operatorList.add(goalState.getOperator());
        findDirectionsToGoal(goalState.getParent());
    }

    public static void printDirectionsToGoal(AbstractNode result) {
        findDirectionsToGoal(result);

        Collections.reverse(operatorList);
        System.out.println(operatorList);
        operatorList.clear();
    }

    public static void printAlgorithmInfo(SearchStrategy searchStrategy, AbstractNode state, HeuristicType heuristicType) throws WrongStrategyException {
        long startTime = System.currentTimeMillis();

        System.out.println(String.format("\n\n%s heuristic:", heuristicType.getHeuristicName()));

        searchStrategy.chooseHeuristic(heuristicType);
        AbstractNode result = searchStrategy.search(state);

        long stopTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        System.out.println("Time taken: " + (stopTime - startTime));
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()));

        printStatesToGoal(result);
        printDirectionsToGoal(result);
    }

    public static void chooseStrategy(SearchStrategyType searchStrategyType) throws WrongStrategyException {
        BoardState board = new BoardState();
        System.out.println(board);

        AbstractNode state = null;
        SearchStrategy searchStrategy = null;
        switch (searchStrategyType){
            case A_STAR:
                state = new ANode(null, board, DirectionOperator.START, 0, 0);
                searchStrategy = new AStarSearch();
                break;
            case BEST_FIRST:
                state = new Node(null, board, DirectionOperator.START, 0);
                searchStrategy = new BestFirstSearch();
                break;
        }
        printAlgorithmInfo(searchStrategy, state, HeuristicType.MANHATTAN);
        printAlgorithmInfo(searchStrategy, state, HeuristicType.NUMBER_UNORDERED);
    }

    public static void main(String[] args) {
        try {
            chooseStrategy(SearchStrategyType.A_STAR);
            //chooseStrategy(SearchStrategyType.BEST_FIRST);
        } catch (WrongStrategyException wrongStrategy) {
            wrongStrategy.printStackTrace();
        }
    }
}
