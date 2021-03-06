package boardgame.board;

import java.util.Arrays;
import java.util.Random;

public class BoardState {
    private int emptyTileIndex; // индекс на празната плочка
    private final int TILES_LENGTH = 9;
    private int[] tilesArray;

    public BoardState(int[] tilesArray){
        setTilesArray(tilesArray);
    }

    public BoardState(){
       generateRandomTiles();
    }

    public BoardState(BoardState other){
        this(other.tilesArray);
    }

    public int[] getTilesArray() {
        return tilesArray;
    }

    public void setTilesArray(int[] tilesArray) { // дълбоко копиране на масива с плочките
       if(tilesArray != null && tilesArray.length == TILES_LENGTH){
           this.tilesArray = new int[TILES_LENGTH];
           for(int i = 0; i < TILES_LENGTH; i++){
               this.tilesArray[i] = tilesArray[i];
               if(tilesArray[i] == 0){
                   this.emptyTileIndex = i; // задаване на индекса на празната плочка
               }
           }
       }
       else{
           generateRandomTiles();
       }
    }


    private void generateRandomTiles(){
        this.tilesArray = new int[TILES_LENGTH];
        Random generator = new Random();
        int uniqueTile;
        boolean hasTile;
        for(int i = 0; i < TILES_LENGTH; i++){
            do {
                hasTile = false;
                uniqueTile = generator.nextInt(TILES_LENGTH);

                for (int j = 0; j < i; j++) {
                    if (uniqueTile == tilesArray[j]) {
                        hasTile = true;
                        break;
                    }
                }
            } while (hasTile);

            tilesArray[i] = uniqueTile;
            if(uniqueTile == 0){
                this.emptyTileIndex = i;
            }
        }
    }

    /*
        метод, който проверяа дали плочките се намират в своето целево състояние
        за целео състояние приемам следното разположение на елементите: 0,1,2,3,4,5,6,7,8
        0 1 2
        3 4 5
        6 7 8
     */
    public boolean isFinalState(){
        for(int i = 0; i < TILES_LENGTH; i++){
            if(this.tilesArray[i] != i){
                return false;
            }
        }
        return true;
    }

    /*
        невалидни индекси за местене в случая са: 0, 3, 6
     */
    public BoardState swapLeft(){
        if(!checkSwaps(0,3,6)){
            return swapWithEmptyTile(DirectionOperator.LEFT);
        }
        return null;
    }

    /*
       невалидни индекси за местене в случая са: 2, 5, 8
    */
    public BoardState swapRight(){
        if(!checkSwaps(2,5,8)){
            return swapWithEmptyTile(DirectionOperator.RIGHT);
        }
        return null;
    }

    /*
   невалидни индекси за местене в случая са: 0, 1, 2
    */
    public BoardState swapUp(){
        if(!checkSwaps(0,1,2)){
            return swapWithEmptyTile(DirectionOperator.UP);
        }
        return null;
    }

    /*
    невалидни индекси за местене в случая са: 0, 1, 2
    */
    public BoardState swapDown(){
        if(!checkSwaps(6,7,8)){
            return swapWithEmptyTile(DirectionOperator.DOWN);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~~~BOARD~~~\n   ");
        for(int i = 1; i <= TILES_LENGTH; i++){
            stringBuilder.append(String.format("%d ", tilesArray[i - 1]));
            if(i % 3 == 0 && i != TILES_LENGTH){
                stringBuilder.append("\n   ");
            }
        }
        stringBuilder.append("\n~~~~~~~~~~~");
        return stringBuilder.toString();
    }

    private boolean checkSwaps(int index1, int index2, int index3){
        return emptyTileIndex == index1 || emptyTileIndex == index2 || emptyTileIndex == index3;
    }

    private BoardState swapWithEmptyTile(DirectionOperator direction){
        BoardState copyBoard = new BoardState(this);

        int indexToSwap = copyBoard.emptyTileIndex;
        int newEmptyTileIndex = indexToSwap;

        switch (direction){
            case LEFT: newEmptyTileIndex = indexToSwap - 1; break;
            case RIGHT: newEmptyTileIndex = indexToSwap + 1; break;
            case UP: newEmptyTileIndex = indexToSwap - 3; break;
            case DOWN: newEmptyTileIndex = indexToSwap + 3;  break;
        }

        copyBoard.tilesArray[indexToSwap] = copyBoard.tilesArray[newEmptyTileIndex];
        copyBoard.tilesArray[newEmptyTileIndex] = 0;
        copyBoard.emptyTileIndex = newEmptyTileIndex;

        return copyBoard;
    }

    public String tilesArrayConfiguration(){
        return Arrays.toString(tilesArray);
    }
}
