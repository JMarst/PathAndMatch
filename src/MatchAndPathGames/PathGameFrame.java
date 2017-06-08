/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jon
 */
class PathGameFrame extends GameFrame
        implements GameGUI {

    PathGameBoard pathgameboard;
    boolean dontAllowCPUOption;

    public PathGameFrame(PathGameBoard gameboard, String gameType, PathGame pathGame) {
        super(gameboard, gameType);
        this.pathgameboard = gameboard;
        font1 = new Font("SansSerif", Font.BOLD, 20);
        inputPlayerName();
        createInfoBox();
        createButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Component createButtons() {
        JPanel resetPanel;
        JButton resetButton, hiScoreButton;

        createBoard();

        spacePanel.add(pathgameboard.boardPanel, BorderLayout.CENTER);

        resetPanel = new JPanel();
        resetButton = new JButton(new ResetButtonAction());
        resetButton.setPreferredSize(new Dimension(75, 25));
        resetButton.setText("Reset");
        resetPanel.add(resetButton);

        hiScoreButton = new JButton(new HiScoreButtonAction());
        hiScoreButton.setPreferredSize(new Dimension(125, 25));
        hiScoreButton.setText("See HiScores");
        resetPanel.add(hiScoreButton);

        spacePanel.add(resetPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        return spacePanel;
    }

    public void createBoard() {
        pathgameboard.boardPanel.removeAll();
        pathgameboard.boardPanel.updateUI();
        buttonArray = new JButton[pathgameboard.getRowCount()][pathgameboard.getColumnCount()];
        pathgameboard.pressedReset();
        for (int i = 0; i < pathgameboard.getRowCount(); i++) {
            for (int j = 0; j < pathgameboard.getColumnCount(); j++) {
                JButton button = new JButton(new GameAction(i, j));
                buttonArray[i][j] = button;
                pathgameboard.boardPanel.add(button);
                button.setEnabled(true);
            }
        }
        //If the CPU is player 1
        if ((!pathgameboard.player2Turn && player1IsCPU)) {
            Random r = new Random();
            int row = r.nextInt(pathgameboard.getRowCount());
            int col = r.nextInt(pathgameboard.getColumnCount());
            buttonArray[row][col].doClick();
        }
    }

    
    /**
     * Used for the start of the game. Brings up JOptionPanes to determine
     * Whether the player's are human or computer, and if they are human then
     * brings up a JOptionPane to input their name.
     */  
    public void inputPlayerName() {
        Object[] possibilities = {"Player", "Computer"};
        String s = (String) JOptionPane.showInputDialog(
                this,
                "Player 1 Type: ",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Player");
        String player1Choice = s;
        //To avoid NullPointerException when pressing the X button
        if(player1Choice == null) {
            s = "Player";
        }
        if (s.equals("Computer")) {
            player1Name = "Computer";
            player1IsCPU = true;
            //Used so that the second player choice must be a human
            //(program doesn't allow CPU vs CPU)
            dontAllowCPUOption = true;
        } else {
            player1IsCPU = false;
            String y = (String) JOptionPane.showInputDialog(
                    this,
                    "Player 1 Name: ",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE);
            player1Name = y;
            if (player1Name == null) {
                player1Name = "?";
            }
        }
        if (dontAllowCPUOption) {
            possibilities = new Object[]{"Player"};
        }
        String t = (String) JOptionPane.showInputDialog(
                this,
                "Player 2 Type: ",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Player");
        String player2Choice = t;
        if(player2Choice == null) {
            t = "Player";
        }
        if (t.equals("Computer")) {
            player2Name = "Computer";
            player2IsCPU = true;
        } else {
            player2IsCPU = false;
            String z = (String) JOptionPane.showInputDialog(
                    this,
                    "Player 2 Name: ",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE);
            player2Name = z;
            if (player2Name == null) {
                player2Name = "??";
            }
        }
    }

    /**
     * Used to change player 1 during game play.
     */
    @Override
    void changePlayer1() {
        pathgameboard.addScore(player1Name, gameboard.player1Score);
        pathgameboard.addScore(player2Name, gameboard.player2Score);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[] possibilities = {"Player", "Computer"};
        if (player2IsCPU) {
            possibilities = new String[]{"Player"};
        }
        String s = (String) JOptionPane.showInputDialog(
                this,
                "Player 1 Type: ",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Player");
        String player1Choice = s;
        if(player1Choice == null) {
            s = "Player";
        }
        if (s.equals("Computer")) {
            player1Name = "Computer";
            player1.setText(player1Name);
            player1IsCPU = true;
        } else {
            player1IsCPU = false;
            String y = (String) JOptionPane.showInputDialog(
                    this,
                    "Player 1 Name: ",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE);
            player1Name = y;
            if (player1Name == null) {
                player1Name = "?";
            }
            player1.setText("" + player1Name);
        }
        createBoard();
        pathgameboard.player1Score = 0;
        pathgameboard.ui.setPlayer1Score(pathgameboard.player1Score);
        pathgameboard.player2Score = 0;
        pathgameboard.ui.setPlayer2Score(pathgameboard.player2Score);
    }

    /**
     * Used to change player 2 during game play.
     */
    @Override
    void changePlayer2() {
        pathgameboard.addScore(player1Name, gameboard.player1Score);
        pathgameboard.addScore(player2Name, gameboard.player2Score);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[] possibilities = {"Player", "Computer"};
        if (player1IsCPU) {
            possibilities = new String[]{"Player"};
        }
        String t = (String) JOptionPane.showInputDialog(
                this,
                "Player 2 Type: ",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Player");
        String player2Choice = t;
        if(player2Choice == null) {
            t = "Player";
        }
        if (t.equals("Computer")) {
            player2Name = "Computer";
            player2.setText(player2Name);
            player2IsCPU = true;
        } else {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            player2IsCPU = false;
            String z = (String) JOptionPane.showInputDialog(
                    this,
                    "Player 2 Name: ",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE);
            player2Name = z;
            if (player2Name == null) {
                player2Name = "??";
            }
            player2.setText("" + player2Name);
        }
        createBoard();
        pathgameboard.player1Score = 0;
        pathgameboard.ui.setPlayer1Score(pathgameboard.player1Score);
        pathgameboard.player2Score = 0;
        pathgameboard.ui.setPlayer2Score(pathgameboard.player2Score);
    }

    class GameAction extends AbstractAction {

        private final int row;
        private final int column;
        Random r = new Random();

        GameAction(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pathgameboard.pressedBoard(row, column);
            //Updates the occupied spaces using colour coding
            //Also sets all buttons as uneditable for each turn
            for (int i = 0; i < pathgameboard.getRowCount(); i++) {
                for (int j = 0; j < pathgameboard.getColumnCount(); j++) {
                    buttonArray[i][j].setEnabled(false);
                    if (pathgameboard.tableData[i][j].equals("Player 1's Space")) {
                        buttonArray[i][j].setBackground(Color.GREEN);
                        buttonArray[i][j].setText(Integer.toString(pathgameboard.getTurnCount()));
                        buttonArray[i][j].setForeground(Color.RED);
                        pathgameboard.tableData[i][j] = "Information Set.";
                    }
                    if (pathgameboard.tableData[i][j].equals("Player 2's Space")) {
                        buttonArray[i][j].setBackground(Color.RED);
                        buttonArray[i][j].setText(Integer.toString(pathgameboard.getTurnCount()));
                        buttonArray[i][j].setForeground(Color.WHITE);
                        pathgameboard.tableData[i][j] = "Information Set.";
                    }
                }
            }

            //Updates the available editable buttons for each turn.
            if (pathgameboard.space1 == true) {
                buttonArray[pathgameboard.getAvailableRow1()][pathgameboard.getAvailableColumn1()].setEnabled(true);
            }
            if (pathgameboard.space2 == true) {
                buttonArray[pathgameboard.getAvailableRow2()][pathgameboard.getAvailableColumn2()].setEnabled(true);
            }
            if (pathgameboard.space3 == true) {
                buttonArray[pathgameboard.getAvailableRow3()][gameboard.getAvailableColumn3()].setEnabled(true);
            }
            if (pathgameboard.space4 == true) {
                buttonArray[pathgameboard.getAvailableRow4()][pathgameboard.getAvailableColumn4()].setEnabled(true);
            }

            //Conditions for a CPU's turn
            //If there are no spaces available for the CPU, move to the winning conditions
            if (pathgameboard.CPURowArray.isEmpty()) {
            } else {
                if ((!pathgameboard.player2Turn && player1IsCPU) || ((pathgameboard.player2Turn && player2IsCPU))) {
                    buttonArray[pathgameboard.CPUChosenRow][pathgameboard.CPUChosenColumn].doClick();
                }
            }

            //Winning Conditions 
            if ((pathgameboard.player1Victory == true) || ((pathgameboard.getTurnCount() == pathgameboard.getTotalCellCount()) && pathgameboard.player2Turn == true)) {
                JOptionPane.showMessageDialog(null, "Player 1 Wins!");
                scoreChecker();
                pathgameboard.player1Score = pathgameboard.player1Score + tempScoreCounter;
                pathgameboard.ui.setPlayer1Score(pathgameboard.player1Score);
                tempScoreCounter = 0;
                createBoard();
            }

            if ((pathgameboard.player2Victory == true) || (pathgameboard.getTurnCount() == pathgameboard.getTotalCellCount()) && pathgameboard.player2Turn == false) {
                JOptionPane.showMessageDialog(null, "Player 2 Wins!");
                scoreChecker();
                pathgameboard.player2Score = pathgameboard.player2Score + tempScoreCounter;
                pathgameboard.ui.setPlayer2Score(pathgameboard.player2Score);
                tempScoreCounter = 0;
                createBoard();
            }
            turnUpdate();
        }
    }

    class ResetButtonAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            createBoard();
        }
    }

    class HiScoreButtonAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            JDialog dialog = new JDialog();
            dialog.setTitle("HiScores");
            Font font1 = new Font("SansSerif", Font.BOLD, 25);
            HiScore hs;
            //Reads the hiScores from the hiScores ArrayList, then
            //puts them in the JDialog box.
            for (int i = 0; i < pathgameboard.hiScores.size(); i++) {
                hs = pathgameboard.hiScores.get(i);
                String s = ((i + 1) + "    " + hs.toString());
                JTextField tf = new JTextField(s);
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setEditable(false);
                tf.setFont(font1);
                tf.setSize(150, 50);
                panel.add(tf);
                dialog.add(panel);
            }
            dialog.setVisible(true);
        }

    }

}
