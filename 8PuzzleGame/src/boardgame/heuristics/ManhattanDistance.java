package boardgame.heuristics;

import boardgame.board.BoardState;

public class ManhattanDistance implements Heuristics{
    private static final int NUMBER = 3;
    private static final int TILES_COUNT = 9;

    // getXCoord(tiles[i]), getYCoord(tiles[i]) - Х и Y координатите на разглежданата плочка в оценяваното състояние
    // getXCoord(i), getYCoord(i) - Х и Y координатите на разглежданата плочка в целевото състояние
    public int calculate(BoardState board) {
        int calculatedSum = 0;
        int[] tiles = board.getTilesArray();

        int distanceByX, distanceByY, currentDistance = 0;
        for (int i = 0; i < TILES_COUNT; i++) {
            if (tiles[i] != i && tiles[i] != 0) {
                distanceByX = Math.abs(getXCoord(tiles[i]) - getXCoord(i));
                distanceByY = Math.abs(getYCoord(tiles[i]) - getYCoord(i));
                currentDistance = distanceByX + distanceByY;
                calculatedSum += currentDistance;
            }
        }

        return calculatedSum;
    }

    private static int getXCoord(int index) {
        return index % NUMBER;
    }

    private static int getYCoord(int index) {
        return index / NUMBER;
    }
}
