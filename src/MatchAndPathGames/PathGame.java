/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.util.Scanner;

/**
 *
 * @author Jon
 */
public class PathGame {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Choose the number of columns: ");
        int columns = scanner.nextInt();
        String gameType = "Path Game";
        PathGame pathGame = new PathGame();
        PathGameBoard gameboard;
        gameboard = new PathGameBoard(rows, columns);        
        PathGameFrame pathGameFrame = new PathGameFrame(gameboard, gameType, pathGame);
        gameboard.setGUI(pathGameFrame);
        gameboard.readHiScores();
    }
    
}
