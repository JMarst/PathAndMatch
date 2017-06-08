/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

/**
 *
 * @author Jon
 */
public class MatchGameBoard extends GameBoard {

    public MatchGameBoard(int rows, int columns) {
        super(rows, columns);
    }

    public void pressedBoard(int row, int column) {
        if (super.getTurnCount() % 2 == 0) {  //Code executed is very different depending on whose turn it is
            //Variables used to check whether an adjacent space next to the previously placed space is available.
            space1 = false;
            space2 = false;
            space3 = false;
            space4 = false;
            tableData[row][column] = "Player 1's Space";
            if (row != getRowCount() - 1) {          // Taking into account possible OutOfBoundsException
                victoryChecker1 = tableData[row + 1][column];
                if ("".equals(victoryChecker1)) {
                    availableRow1 = row + 1;
                    availableColumn1 = column;
                    space1 = true;
                }
            }
            if (column != getColumnCount() - 1) {    // Taking into account possible OutOfBoundsException
                victoryChecker2 = tableData[row][column + 1];
                if ("".equals(victoryChecker2)) {
                    availableRow2 = row;
                    availableColumn2 = column + 1;
                    space2 = true;
                }
            }
            if (row != 0) {           // Taking into account possible OutOfBoundsException
                victoryChecker3 = tableData[row - 1][column];
                if ("".equals(victoryChecker3)) {
                    availableRow3 = row - 1;
                    availableColumn3 = column;
                    space3 = true;
                }
            }
            if (column != 0) {       // Taking into account possible OutOfBoundsException
                victoryChecker4 = tableData[row][column - 1];
                if ("".equals(victoryChecker4)) {

                    availableRow4 = row;
                    availableColumn4 = column - 1;
                    space4 = true;
                }
            }
            if ((space1 == false) && (space2 == false) && (space3 == false) && (space4 == false)) {
                //Determines whether or not player 2 will have no places to go, taking into account corner spaces.
                player1Victory = true;
                scoreChecker();
                player1TestScore = player1TestScore + tempScoreCounter;
            }
            player2Turn = true;
        } else {   //Player 2's turn implementation
            if ((tableData[row][column].equals(tableData[availableRow1][availableColumn1]) && (space1 == true))) {
                tableData[row][column] = "Player 2's Space";
            }
            if ((tableData[row][column].equals(tableData[availableRow2][availableColumn2]) && (space2 == true))) {
                tableData[row][column] = "Player 2's Space";
            }
            if ((tableData[row][column].equals(tableData[availableRow3][availableColumn3]) && (space3 == true))) {
                tableData[row][column] = "Player 2's Space";
            }
            if ((tableData[row][column].equals(tableData[availableRow4][availableColumn4]) && (space4 == true))) {
                tableData[row][column] = "Player 2's Space";
            }
            player2Turn = false;
        }
        turnCount++;
    }
    
    public void pressedChangePlayer1() {
        changePlayer1 = true;
    }
    
    public void pressedChangePlayer2() {
        changePlayer2 = true;
    }

}
