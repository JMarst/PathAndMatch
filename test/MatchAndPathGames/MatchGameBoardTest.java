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
public class MatchGameBoardTest {
    
    public MatchGameBoardTest() {
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
     * Test of pressedBoard method, of class MatchGameBoard.
     */
    @Test
    public void testPressedBoard() {
        System.out.println("pressedBoard");
        int row = 5;
        int column = 5;
        MatchGameBoard instance = new MatchGameBoard(row, column);
        

        //First turn, player 1's turn
        instance.pressedBoard(1, 4);
        assertEquals("Player 1's Space", instance.tableData[1][4]);
        assertEquals(1, instance.turnCount);
        assertEquals(true, instance.player2Turn);
        
        
        //Second turn, player 2's turn
        instance.pressedBoard(1, 3);
        assertEquals("Player 2's Space", instance.tableData[1][3]);
        assertEquals(2, instance.turnCount);
        assertEquals(false, instance.player2Turn);
        
        
        //Third turn, player 1's turn
        instance.pressedBoard(0, 3);
        assertEquals("Player 1's Space", instance.tableData[0][3]);
        assertEquals(3, instance.turnCount);
        assertEquals(true, instance.player2Turn);
        
        
        //Fourth turn, player 2's turn
        instance.pressedBoard(0, 2);
        assertEquals("Player 2's Space", instance.tableData[0][2]);
        assertEquals(4, instance.turnCount);
        assertEquals(false, instance.player2Turn);
        
        
        //Fifth and final turn, player 1's turn
        instance.pressedBoard(0, 4);
        assertEquals("Player 1's Space", instance.tableData[0][4]);    
        assertEquals(5, instance.turnCount);
        assertEquals(true, instance.player2Turn);
        
        
        //Player 1 has won, check for victory
        assertEquals(true, instance.player1Victory);
    }
    
    @Test
    public void testPressedReset() {
        System.out.println("Pressed Reset");
        int row = 5;
        int column = 5;
        MatchGameBoard instance = new MatchGameBoard(row, column);
        
        instance.tableData[2][2] = "test string";
        assertEquals("test string", instance.tableData[2][2]);
        
        //Will erase all data from the table
        instance.pressedReset();
        assertEquals("", instance.tableData[2][2]);
    }
    
    /**
     * Test of addScore method, of class GameBoard.
     * This test simulates a whole game, and changes players at the end.
     * Also checks whose turn it is using a variable.
     */
    @Test
    public void testAddScore() {
        System.out.println("addScore");
                          
        int row = 5;
        int column = 5;
        MatchGameBoard instance = new MatchGameBoard(row, column);
        
        //Used for testing later on       
        instance.deleteHiScores();
        
        //First turn, player 1's turn
        instance.pressedBoard(1, 4);
        assertEquals("Player 1's Space", instance.tableData[1][4]);
        assertEquals(1, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn);      
        //Second turn, player 2's turn
        instance.pressedBoard(1, 3);
        assertEquals("Player 2's Space", instance.tableData[1][3]);
        assertEquals(2, instance.turnCount);
        //Checks that it is going to be player 1's turn next turn
        assertEquals(false, instance.player2Turn);    
        //Third turn, player 1's turn
        instance.pressedBoard(0, 3);
        assertEquals("Player 1's Space", instance.tableData[0][3]);
        assertEquals(3, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn); 
        //Fourth turn, player 2's turn
        instance.pressedBoard(0, 2);
        assertEquals("Player 2's Space", instance.tableData[0][2]);
        assertEquals(4, instance.turnCount);
        //Checks that it is going to be player 1's turn next turn
        assertEquals(false, instance.player2Turn);  
        //Fifth and final turn, player 1's turn
        instance.pressedBoard(0, 4);
        assertEquals("Player 1's Space", instance.tableData[0][4]);    
        assertEquals(5, instance.turnCount);
        //Checks that it is going to be player 2's turn next turn
        assertEquals(true, instance.player2Turn);
        
        //Player 1 has won, check for victory
        assertEquals(true, instance.player1Victory);
        assertEquals(20, instance.player1TestScore);
        
        //Player 1 is being changed, score stored in system was 10, now is 20   
        
        //Previous score
        instance.addScore("player1", 10);
        assertEquals(instance.hiScores.get(0).getScore(), 10);
        assertTrue(instance.hiScores.size() == 1);
        
        //New score
        instance.addScore("player1", 20);
        assertEquals(instance.hiScores.get(0).getScore(), 20);
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
}
