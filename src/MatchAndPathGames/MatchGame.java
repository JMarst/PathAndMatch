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
public class MatchGame {
     /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Choose the number of columns: ");
        int columns = scanner.nextInt();
        String gameType = "Match Game";
        MatchGame matchGame = new MatchGame();
        MatchGameBoard gameboard;
        gameboard = new MatchGameBoard(rows, columns);
        MatchGameFrame matchGameFrame = new MatchGameFrame(gameboard, gameType, matchGame);
        gameboard.setGUI(matchGameFrame);
        gameboard.readHiScores();
    }
   
}
