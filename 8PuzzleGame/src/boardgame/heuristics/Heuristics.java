package boardgame.heuristics;

import boardgame.board.BoardState;

public interface Heuristics {
    int calculate(BoardState board);
}
