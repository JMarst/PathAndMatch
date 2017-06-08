/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jon
 */
public class PathGameBoard extends GameBoard {

    ArrayList<Integer> CPURowArray;
    ArrayList<Integer> CPUColumnArray;
    int CPUChosenRow;
    int CPUChosenColumn;
    private final Random r;

    public PathGameBoard(int rows, int columns) {
        super(rows, columns);
        r = new Random();
    }

    public void pressedBoard(int row, int column) {
        //Variables used to check whether an adjacent space next to the previously placed space is available.
        space1 = false;
        space2 = false;
        space3 = false;
        space4 = false;
        CPURowArray = new ArrayList<>();
        CPUColumnArray = new ArrayList<>();

        if (turnCount == 0) {
            //If it is the first turn, the space selected will definitely be player 1's space.
            tableData[row][column] = "Player 1's Space";
        }

        if (turnCount % 2 == 0) {
            //Checks whose turn it is with a conditional that alternates between true and false.
            player2Turn = true;
        } else {
            player2Turn = false;
        }

        if (row != getRowCount() - 1) {          // Taking into account possible OutOfBoundsException
            victoryChecker1 = tableData[row + 1][column];
            if ("".equals(victoryChecker1)) {  //Checks if the space is occupied by an already used space.          
                //Keeps data of the available space, and uses a boolean for future reference.
                availableRow1 = row + 1;
                availableColumn1 = column;
                //Insert available space into collection of potential
                //CPU spaces for next turn 
                CPURowArray.add(availableRow1);
                CPUColumnArray.add(availableColumn1);
                space1 = true;
            }
        }
        if (column != getColumnCount() - 1) {
            victoryChecker2 = tableData[row][column + 1];
            if ("".equals(victoryChecker2)) {
                availableRow2 = row;
                availableColumn2 = column + 1;
                space2 = true;
                CPURowArray.add(availableRow2);
                CPUColumnArray.add(availableColumn2);
            }
        }

        if (row != 0) {
            victoryChecker3 = tableData[row - 1][column];
            if ("".equals(victoryChecker3)) {
                availableRow3 = row - 1;
                availableColumn3 = column;
                space3 = true;
                CPURowArray.add(availableRow3);
                CPUColumnArray.add(availableColumn3);
            }
        }
        if (column != 0) {
            victoryChecker4 = tableData[row][column - 1];
            if ("".equals(victoryChecker4)) {
                availableRow4 = row;
                availableColumn4 = column - 1;
                space4 = true;
                CPURowArray.add(availableRow4);
                CPUColumnArray.add(availableColumn4);
            }
        }

        //If there are available spaces for the CPU, choose a random one
        if (!CPURowArray.isEmpty()) {
            int index = r.nextInt(CPURowArray.size());
            CPUChosenRow = CPURowArray.get(index);
            CPUChosenColumn = CPUColumnArray.get(index);
        }

        if ((space1 == false) && (space2 == false) && (space3 == false) && (space4 == false)) {
            //If this conditional is true, the player whose turn it is has won the game as there are no available spaces 
            //for the next turn.
            if (player2Turn) {
                tableData[row][column] = "Player 1's Space";
                player1Victory = true;
                scoreChecker();
                player1TestScore = player1TestScore + tempScoreCounter;
            } else {
                tableData[row][column] = "Player 2's Space";
                player2Victory = true;
                scoreChecker();
                player2TestScore = player2TestScore + tempScoreCounter;
            }
        }

        //These are the commands for every turn after the first.
        if ((tableData[row][column].equals(tableData[availableRow1][availableColumn1]) && (space1 == true))) {
            if (player2Turn) {
                tableData[row][column] = "Player 1's Space";
            } else {
                tableData[row][column] = "Player 2's Space";
            }
        }
        if ((tableData[row][column].equals(tableData[availableRow2][availableColumn2]) && (space2 == true))) {
            if (player2Turn) {
                tableData[row][column] = "Player 1's Space";
            } else {
                tableData[row][column] = "Player 2's Space";
            }
        }
        if ((tableData[row][column].equals(tableData[availableRow3][availableColumn3]) && (space3 == true))) {
            if (player2Turn) {
                tableData[row][column] = "Player 1's Space";
            } else {
                tableData[row][column] = "Player 2's Space";
            }
        }
        if ((tableData[row][column].equals(tableData[availableRow4][availableColumn4]) && (space4 == true))) {
            if (player2Turn) {
                tableData[row][column] = "Player 1's Space";
            } else {
                tableData[row][column] = "Player 2's Space";
            }
        }
        turnCount++;
    }

}
