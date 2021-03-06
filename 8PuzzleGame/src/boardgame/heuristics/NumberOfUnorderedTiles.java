package boardgame.heuristics;

import boardgame.board.BoardState;

public class NumberOfUnorderedTiles implements Heuristics{

    public int calculate(BoardState board){
        int calculatedSum = 0;
        int[] tiles = board.getTilesArray();

        for(int i = 0; i < tiles.length; i++){
            if(tiles[i] != i && tiles[i] != 0){
                calculatedSum++;
            }
        }
        return calculatedSum;
    }
}
