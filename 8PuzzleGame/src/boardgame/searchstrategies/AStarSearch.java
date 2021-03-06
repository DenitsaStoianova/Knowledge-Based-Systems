package boardgame.searchstrategies;

import boardgame.board.BoardState;
import boardgame.board.DirectionOperator;
import boardgame.exceptions.WrongStrategyException;
import boardgame.node.ANode;
import boardgame.node.AbstractNode;

import java.util.PriorityQueue;

public class AStarSearch extends SearchStrategy{
    private final PriorityQueue<AbstractNode> frontier;

    public AStarSearch() {
        frontier = new PriorityQueue<>();
    }

    @Override
    public void addToFrontier(BoardState board, DirectionOperator direction){
        if(board != null){
            frontier.add(new ANode(getCurrentNode(), board, direction, getHeuristic().calculate(board), getCurrentNode().getPathCost() + 1));
        }
    }

    @Override
    public AbstractNode search(AbstractNode initialState) throws WrongStrategyException {
        frontier.add(initialState);

        while(!frontier.isEmpty()){
            setCurrentNode(frontier.poll());

            if(getCurrentNode().isFinalState()){
                System.out.println(">>>GOAL<<<");
                return getCurrentNode();
            }

            setCurrentBoard(getCurrentNode().getBoardState());
            setLastSwap(getCurrentNode().getOperator());

            clearBoards();
            generateChildren();

            addToFrontier(getLeftSwappedBoard(), DirectionOperator.LEFT);
            addToFrontier(getRightSwappedBoard(), DirectionOperator.RIGHT);
            addToFrontier(getUpSwappedBoard(), DirectionOperator.UP);
            addToFrontier(getDownSwappedBoard(), DirectionOperator.DOWN);
        }
        throw new WrongStrategyException("Failure, Frontier cannot be empty!");
    }
}
