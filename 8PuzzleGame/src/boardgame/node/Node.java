package boardgame.node;

import boardgame.board.BoardState;
import boardgame.board.DirectionOperator;

public class Node extends AbstractNode {
    public Node(AbstractNode parent, BoardState board, DirectionOperator operator, int heuristic) {
        super(parent, board, operator, heuristic);
    }

    @Override
    public int compareTo(AbstractNode other) {
        if(super.getHeuristic() < other.getHeuristic()) {
            return -1;
        }
        return 1;
    }
}
