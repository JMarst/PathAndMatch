/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jon
 */
public class GameBoard extends AbstractTableModel {

    String[][] tableData;
    String[] columnData;
    JPanel boardPanel;
    JButton button;
    int turnCount;
    GameGUI ui;
    String victoryChecker1;
    String victoryChecker2;
    String victoryChecker3;
    String victoryChecker4;
    int availableRow1;
    int availableColumn1;
    int availableRow2;
    int availableColumn2;
    int availableRow3;
    int availableColumn3;
    int availableRow4;
    int availableColumn4;
    boolean space1;
    boolean space2;
    boolean space3;
    boolean space4;
    boolean player1Victory;
    boolean player2Victory;
    boolean player2Turn;
    int player1Score;
    int player2Score;
    int player1TestScore;
    int player2TestScore;
    ArrayList<HiScore> hiScores;
    boolean changePlayer1;
    boolean changePlayer2;
    int tempScoreCounter;

    //File location where HiScores will be stored
    public static final String HI_SCORE_FILE = "highscores";

    public GameBoard(int rows, int columns) {
        tableData = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tableData[i][j] = "";
            }
        }
        columnData = new String[columns];
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, columns));
        turnCount = 0;
        hiScores = new ArrayList<>();
    }

    /**
     * Will try reading from the file, error occurs if this cannot be done
     * Then sorts HiScore collection into descending order using a separate method
     */
    public void readHiScores() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(HI_SCORE_FILE));
            hiScores = (ArrayList<HiScore>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ui.createWarning("Error Loading High Scores",
                    "Can't access high scores file!");
        }
        sortHiScores();
    }

    /**
     * Writes to the file to update what has been added
     */
    public void updateHiScores() {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(HI_SCORE_FILE));
            oos.writeObject(hiScores);
        } catch (FileNotFoundException e) {
            ui.createWarning("Error Loading High Scores",
                    "Can't access high scores file!");
        } catch (IOException ex) {
            ui.createWarning("Error Loading High Scores",
                    "Can't access high scores file!");
        }
    }

    /**
     * Sorts the HiScore collection into descending order
     */
    public void sortHiScores() {
        HiScoreComparer comparer = new HiScoreComparer();
        Collections.sort(hiScores, comparer);
    }

    /**
     * Adds a new HiScore to the list 
     * Updates the file with the new HiScore if it is higher than the previous one
     * @param name Name of the player being added
     * @param score The score that the player has ended their game with
     */
    public void addScore(String name, int score) {
        if (hiScores == null) {
        } else {
            for (HiScore h : hiScores) {
                if (name.equals(h.getName())) {
                    //If the player got a higher score than they did previously
                    if ((score > h.getScore())) {
                        int index = hiScores.indexOf(h);
                        hiScores.remove(index);
                    //Whole file is deleted, then updated later on with the
                        //new player score and old scores. 
                        deleteHiScores();
                        break;
                    } else {
                        return;
                    }
                }
            }
        }
        hiScores.add(new HiScore(name, score));
        updateHiScores();
        sortHiScores();
    }

    //Used to clear all data stored on the file
    public void deleteHiScores() {
        String tempFile = HI_SCORE_FILE;
        File temp = new File(tempFile);
        temp.delete();
    }

    @Override
    public int getRowCount() {
        return tableData.length;
    }

    @Override
    public int getColumnCount() {
        return columnData.length;
    }

    public int getTotalCellCount() {
        return getRowCount() * getColumnCount();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return tableData[row][column];
    }

    public void setGUI(GameGUI ui) {
        this.ui = ui;
    }

    public void pressedReset() {
        player2Turn = false;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                tableData[i][j] = "";
                player1Victory = false;
                player2Victory = false;
                turnCount = 0;
            }
        }
    }

    //Works out the score for a particular game by counting the empty spaces.
    void scoreChecker() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (tableData[i][j].equals("")) {
                    tempScoreCounter++;
                }
            }
        }
    }

    int getTurnCount() {
        return turnCount;
    }

    boolean isPlayer2Turn() {
        return player2Turn;
    }

    int getAvailableRow1() {
        return availableRow1;
    }

    int getAvailableColumn1() {
        return availableColumn1;
    }

    int getAvailableRow2() {
        return availableRow2;
    }

    int getAvailableColumn2() {
        return availableColumn2;
    }

    int getAvailableRow3() {
        return availableRow3;
    }

    int getAvailableColumn3() {
        return availableColumn3;
    }

    int getAvailableRow4() {
        return availableRow4;
    }

    int getAvailableColumn4() {
        return availableColumn4;
    }

}
