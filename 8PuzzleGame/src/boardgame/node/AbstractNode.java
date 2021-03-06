package boardgame.node;

import boardgame.board.BoardState;
import boardgame.board.DirectionOperator;

public abstract class AbstractNode implements Comparable<AbstractNode>{
    private AbstractNode parent; // състоянието от пространството, на което съответства разглежданата дъска
    private BoardState boardState; // възелът от ДС, като наследник на който е генерирана разглежданата дъска
    private DirectionOperator operator; // операторът, с помощта на който е била генерирана разглежданата дъска
    private int heuristic;

    public AbstractNode(AbstractNode parent, BoardState board, DirectionOperator operator, int heuristic) {
      setParent(parent);
      setBoardState(board);
      setOperator(operator);
      setHeuristic(heuristic);
    }

    public AbstractNode getParent() {
        return parent;
    }

    public void setParent(AbstractNode parent) {
        this.parent = parent;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public void setBoardState(BoardState boardState) {
        this.boardState = boardState;
    }

    public DirectionOperator getOperator() {
        return operator;
    }

    public void setOperator(DirectionOperator operator) {
        this.operator = operator != null ? operator : DirectionOperator.START;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = Math.max(heuristic, 0);
    }

    public boolean isFinalState(){
        return this.boardState.isFinalState();
    }

    public int getPathCost() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("STATE: \n%s \n[Operator: %s] \n[h(n) = %d]",
                this.boardState, this.operator.getDirectionName(), this.heuristic);
    }

    public String getNodeConfiguration(){
        return String.format("STATE: %s [Operator: %s] [h(n) = %d]",
                this.boardState.tilesArrayConfiguration(), this.operator.getDirectionName(), this.heuristic);
    }
}
