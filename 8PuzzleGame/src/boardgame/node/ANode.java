package boardgame.node;

import boardgame.board.BoardState;
import boardgame.board.DirectionOperator;

public class ANode extends AbstractNode {
    private int pathCost; // цената на пътя отначалното състояние до състоянието, съответно на разглежданата дъска

    public ANode(AbstractNode parent, BoardState board, DirectionOperator operator, int heuristic, int pathCost) {
        super(parent, board, operator, heuristic);
        this.pathCost = pathCost;
    }

    @Override
    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    @Override
    public String toString() {
        return String.format("%s [g(n) = %d]", super.toString(), this.pathCost);
    }

    @Override
    public int compareTo(AbstractNode other) {
        if(pathCost + super.getHeuristic() < other.getPathCost() + other.getHeuristic()) {
            return -1;
        }
        return 1;
    }

    @Override
    public String getNodeConfiguration() {
        return String.format("%s [g(n) = %d]", super.getNodeConfiguration(), this.pathCost);
    }
}
