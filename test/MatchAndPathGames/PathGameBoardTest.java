/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jon
 */
public class PathGameBoardTest {

    public PathGameBoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of pressedBoard method, of class PathGameBoard.
     */
    @Test
    public void testPressedBoard() {
        System.out.println("pressedBoard");
        int row = 5;
        int column = 5;
        PathGameBoard instance = new PathGameBoard(row, column);

        //First Turn, player 1's turn
        instance.pressedBoard(3, 4);
        assertEquals("Player 1's Space", instance.tableData[3][4]);
        assertEquals(true, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(1, instance.turnCount);
        assertEquals(true, instance.player2Turn);

        //Second Turn, player 2's turn
        instance.pressedBoard(2, 4);
        assertEquals("Player 2's Space", instance.tableData[2][4]);
        assertEquals(false, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(2, instance.turnCount);
        assertEquals(false, instance.player2Turn);

        //Third Turn, player 1's turn
        instance.pressedBoard(1, 4);
        assertEquals("Player 1's Space", instance.tableData[1][4]);
        assertEquals(false, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(3, instance.turnCount);
        assertEquals(true, instance.player2Turn);

        //Fourth Turn, player 2's turn
        instance.pressedBoard(1, 3);
        assertEquals("Player 2's Space", instance.tableData[1][3]);
        assertEquals(true, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(4, instance.turnCount);
        assertEquals(false, instance.player2Turn);

        //Fifth Turn, player 1's turn
        instance.pressedBoard(0, 3);
        assertEquals("Player 1's Space", instance.tableData[0][3]);
        assertEquals(false, instance.space1);
        assertEquals(true, instance.space2);
        assertEquals(false, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(5, instance.turnCount);
        assertEquals(true, instance.player2Turn);

        //Sixth and final turn, player 2's turn
        instance.pressedBoard(0, 4);
        assertEquals("Player 2's Space", instance.tableData[0][4]);
        assertEquals(false, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(false, instance.space3);
        assertEquals(false, instance.space4);
        assertEquals(6, instance.turnCount);
        assertEquals(false, instance.player2Turn);

        //Player 2 has won; check for victory
        assertEquals(true, instance.player2Victory);
    }

    public void testPressedReset() {
        System.out.println("Pressed Reset");
        int row = 5;
        int column = 5;
        PathGameBoard instance = new PathGameBoard(row, column);

        instance.tableData[2][2] = "test string";
        assertEquals("test string", instance.tableData[2][2]);

        //Will erase all data from the table
        instance.pressedReset();
        assertEquals("", instance.tableData[2][2]);
    }

    /**
     * Test of addScore method, of class GameBoard. This test simulates a whole
     * game, and changes players at the end. 
     * Also checks whose turn it is using a variable.
     */
    @Test
    public void testAddScore() {
        System.out.println("addScore");

        int row = 5;
        int column = 5;
        PathGameBoard instance = new PathGameBoard(row, column);

        //Used for testing later on       
        instance.deleteHiScores();
        
        //First Turn, player 1's turn
        instance.pressedBoard(3, 4);
        assertEquals("Player 1's Space", instance.tableData[3][4]);
        assertEquals(true, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(1, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn);

        //Second Turn, player 2's turn
        instance.pressedBoard(2, 4);
        assertEquals("Player 2's Space", instance.tableData[2][4]);
        assertEquals(false, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(2, instance.turnCount);
        //Checks it is going to be player 1's turn next turn 
        assertEquals(false, instance.player2Turn);

        //Third Turn, player 1's turn
        instance.pressedBoard(1, 4);
        assertEquals("Player 1's Space", instance.tableData[1][4]);
        assertEquals(false, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(3, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn);

        //Fourth Turn, player 2's turn
        instance.pressedBoard(1, 3);
        assertEquals("Player 2's Space", instance.tableData[1][3]);
        assertEquals(true, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(4, instance.turnCount);
        //Checks that it is going to be player 1's turn next turn
        assertEquals(false, instance.player2Turn);

        //Fifth Turn, player 1's turn
        instance.pressedBoard(0, 3);
        assertEquals("Player 1's Space", instance.tableData[0][3]);
        assertEquals(false, instance.space1);
        assertEquals(true, instance.space2);
        assertEquals(false, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(5, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn);

        //Sixth and final turn, player 2's turn
        instance.pressedBoard(0, 4);
        assertEquals("Player 2's Space", instance.tableData[0][4]);
        assertEquals(false, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(false, instance.space3);
        assertEquals(false, instance.space4);
        assertEquals(6, instance.turnCount);
        //Checks that it is going to be player 1's turn next turn
        assertEquals(false, instance.player2Turn);

        //Player 2 has won; check for victory
        assertEquals(true, instance.player2Victory);
        assertEquals(19, instance.player2TestScore);

        //Player 1 is being changed, score stored in system was 10, now is 19   
        //Previous score
        instance.addScore("player1", 10);
        assertEquals(instance.hiScores.get(0).getScore(), 10);
        assertTrue(instance.hiScores.size() == 1);

        //New score
        instance.addScore("player1", 19);
        assertEquals(instance.hiScores.get(0).getScore(), 19);
        assertTrue(instance.hiScores.size() == 1);

        //Player 2 is being changed, but they did not score higher than their
        //previous score
        //Previous score
        instance.addScore("player2", 30);
        assertEquals(instance.hiScores.get(0).getScore(), 30);
        assertTrue(instance.hiScores.size() == 2);

        //New score
        instance.addScore("player2", 25);
        assertEquals(instance.hiScores.get(0).getScore(), 30);
        assertTrue(instance.hiScores.size() == 2);        
    }

    /**
     * Mock game with a human and a CPU player.
     */
    public void testCPUGame() {

        int row = 5;
        int column = 5;
        PathGameBoard instance = new PathGameBoard(row, column);

        //First turn, human player
        instance.pressedBoard(2, 3);
        assertEquals("Player 1's Space", instance.tableData[3][4]);
        assertEquals(true, instance.space1);
        assertEquals(false, instance.space2);
        assertEquals(true, instance.space3);
        assertEquals(true, instance.space4);
        assertEquals(1, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn);

        //Second turn, CPU player
        //Checks that the CPU's move is valid with condition variable
        int CPURow = instance.CPUChosenRow;
        int CPUColumn = instance.CPUChosenColumn;
        instance.pressedBoard(CPURow, CPUColumn);
        boolean Condition = 
                ((CPURow == 1) && (CPUColumn == 3)) 
                || ((CPURow == 2) && (CPUColumn == 2))  
                || ((CPURow == 3) && (CPUColumn == 3))
                || ((CPURow == 2) && (CPUColumn == 4));               
        assertTrue(Condition);       
        
        
        //Another test run, this time to let the CPU win a game 
        
        int row2 = 1;
        int column2 = 2;              
        PathGameBoard instance2 = new PathGameBoard(row2, column2);

        //First turn, human player
        instance2.pressedBoard(0, 0);
        assertEquals("Player 1's Space", instance2.tableData[0][0]);
        assertEquals(false, instance2.space1);
        assertEquals(true, instance2.space2);
        assertEquals(false, instance2.space3);
        assertEquals(false, instance2.space4);
        assertEquals(1, instance2.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance2.player2Turn);

        //Second turn, CPU player
        //Checks that the CPU's move is valid with condition variable
        int CPURow2 = instance2.CPUChosenRow;
        int CPUColumn2 = instance2.CPUChosenColumn;
        instance2.pressedBoard(CPURow, CPUColumn);
        boolean Condition2 = ((CPURow2 == 0) && (CPUColumn2 == 1));              
        assertTrue(Condition2);
        //Player 2 (the CPU) has won; check for victory
        assertEquals(true, instance.player2Victory);      
    }
}
