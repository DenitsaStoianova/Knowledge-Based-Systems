package boardgame.searchstrategies;

import boardgame.board.BoardState;
import boardgame.board.DirectionOperator;
import boardgame.exceptions.WrongStrategyException;
import boardgame.node.AbstractNode;
import boardgame.node.Node;

import java.util.PriorityQueue;

public class BestFirstSearch extends SearchStrategy {
    private PriorityQueue<AbstractNode> frontier;

    public BestFirstSearch() {
        frontier = new PriorityQueue<>();
    }

    @Override
    public void addToFrontier(BoardState board, DirectionOperator direction){
        if(board != null){
            frontier.add(new Node(getCurrentNode(), board, direction, getHeuristic().calculate(board)));
        }
    }

    @Override
    public AbstractNode search(AbstractNode initialNode)  throws WrongStrategyException {
        frontier.add(initialNode);

        while(!frontier.isEmpty()){

           if(frontier.size() >= 1_000_000) {
               System.out.println("Out of memory.");
               return null;
           }

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
        throw new WrongStrategyException("Failure, frontier cannot be empty!");
    }
}
