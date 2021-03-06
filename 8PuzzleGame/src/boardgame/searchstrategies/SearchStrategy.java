
package boardgame.searchstrategies;

import boardgame.board.BoardState;
import boardgame.exceptions.WrongStrategyException;
import boardgame.heuristics.HeuristicType;
import boardgame.heuristics.Heuristics;
import boardgame.heuristics.ManhattanDistance;
import boardgame.heuristics.NumberOfUnorderedTiles;
import boardgame.node.AbstractNode;
import boardgame.board.DirectionOperator;

public abstract class SearchStrategy {
    private AbstractNode currentNode;
    private DirectionOperator lastSwap;

    private BoardState currentBoard;

    private BoardState leftSwappedBoard;
    private BoardState rightSwappedBoard;
    private BoardState upSwappedBoard;
    private BoardState downSwappedBoard;

    private Heuristics heuristic;

    public SearchStrategy(){
    }

    public AbstractNode getCurrentNode() {
        return currentNode;
    }

    public DirectionOperator getLastSwap() {
        return lastSwap;
    }

    public BoardState getCurrentBoard() {
        return currentBoard;
    }

    public BoardState getLeftSwappedBoard() {
        return leftSwappedBoard;
    }

    public BoardState getRightSwappedBoard() {
        return rightSwappedBoard;
    }

    public BoardState getUpSwappedBoard() {
        return upSwappedBoard;
    }

    public BoardState getDownSwappedBoard() {
        return downSwappedBoard;
    }

    public Heuristics getHeuristic() {
        return heuristic;
    }

    public void setCurrentNode(AbstractNode currentNode) {
        this.currentNode = currentNode;
    }

    public void setLastSwap(DirectionOperator lastSwap) {
        this.lastSwap = lastSwap;
    }

    public void setCurrentBoard(BoardState currentBoard) {
        this.currentBoard = currentBoard;
    }

    public void generateChildren(){
        switch (lastSwap) {
            case LEFT -> generateLeft();
            case RIGHT -> generateRight();
            case UP -> generateUp();
            case DOWN -> generateDown();
            default -> generateStart();
        }
    }

    public void clearBoards(){
        leftSwappedBoard = null;
        rightSwappedBoard = null;
        upSwappedBoard = null;
        downSwappedBoard = null;
    }

    public void chooseHeuristic(HeuristicType heuristicType){
        switch (heuristicType) {
            case MANHATTAN -> heuristic = new ManhattanDistance();
            case NUMBER_UNORDERED -> heuristic = new NumberOfUnorderedTiles();
        }
    }

    public abstract void addToFrontier(BoardState board, DirectionOperator direction);

    public abstract AbstractNode search(AbstractNode initialState) throws WrongStrategyException;

    private void generateLeft(){
        leftSwappedBoard = currentBoard.swapLeft();
        upSwappedBoard = currentBoard.swapUp();
        downSwappedBoard = currentBoard.swapDown();
    }

    private void generateRight(){
        rightSwappedBoard = currentBoard.swapRight();
        upSwappedBoard = currentBoard.swapUp();
        downSwappedBoard = currentBoard.swapDown();
    }

    private void generateUp(){
        leftSwappedBoard = currentBoard.swapLeft();
        rightSwappedBoard = currentBoard.swapRight();
        upSwappedBoard = currentBoard.swapUp();
    }

    private void generateDown(){
        leftSwappedBoard = currentBoard.swapLeft();
        rightSwappedBoard = currentBoard.swapRight();
        downSwappedBoard = currentBoard.swapDown();
    }

    private void generateStart(){
        leftSwappedBoard = currentBoard.swapLeft();
        rightSwappedBoard = currentBoard.swapRight();
        upSwappedBoard = currentBoard.swapUp();
        downSwappedBoard = currentBoard.swapDown();
    }
}
