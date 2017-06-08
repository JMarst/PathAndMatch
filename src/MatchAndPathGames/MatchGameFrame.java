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
import java.util.List;
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
class MatchGameFrame extends GameFrame
        implements GameGUI {

    MatchGameBoard matchgameboard;

    public MatchGameFrame(MatchGameBoard gameboard, String gameType, MatchGame matchGame) {
        super(gameboard, gameType);
        this.matchgameboard = gameboard;
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

        spacePanel.add(matchgameboard.boardPanel, BorderLayout.CENTER);

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
   
    /**
     * Used for the start of the game. Brings up JOptionPanes to determine
     * the names of the human players.
     */ 
     public void inputPlayerName() {
        String y = (String)JOptionPane.showInputDialog(
                    this,
                    "Player 1 Name: ",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE);
        player1Name = y;
        //To avoid NullPointerException when pressing the X button
        if(player1Name == null) {
            player1Name = "?";
        }
        String z = (String)JOptionPane.showInputDialog(
                    this,
                    "Player 2 Name: ",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE);
        player2Name = z;
        if(player2Name == null) {
            player2Name = "??";
        }
    }
     
     public void createBoard() {
        gameboard.boardPanel.removeAll();
        gameboard.boardPanel.updateUI();       
        buttonArray = new JButton[gameboard.getRowCount()][gameboard.getColumnCount()];
        gameboard.pressedReset();
        for (int i = 0; i < gameboard.getRowCount(); i++) {
            for (int j = 0; j < gameboard.getColumnCount(); j++) {
                JButton button = new JButton(new GameAction(i, j));
                buttonArray[i][j] = button;
                gameboard.boardPanel.add(button);
                button.setEnabled(true);
            }
        }
    }
     
    @Override
    public void setPlayer1Score(int n) {
        player1ScoreField.setText(Integer.toString(n));
    }

    @Override
    public void setPlayer2Score(int n) {
        player2ScoreField.setText(Integer.toString(n));
    }

    @Override
    public void showHighScores(List<HiScore> hiScores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createWarning(String error_Loading_High_Scores, String cant_access_high_scores_file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class GameAction extends AbstractAction {

        private final int row;
        private final int column;

        GameAction(int row, int column) {
            this.row = row;
            this.column = column;
        }
                 
        @Override
        public void actionPerformed(ActionEvent e) {
            matchgameboard.pressedBoard(row, column);
            
            //Updates the occupied spaces using colour coding
            for (int i = 0; i < gameboard.getRowCount(); i++) {
                for (int j = 0; j < gameboard.getColumnCount(); j++) {
                    if (gameboard.tableData[i][j].equals("Player 1's Space")) {
                        buttonArray[i][j].setBackground(Color.GREEN);
                        buttonArray[i][j].setText(Integer.toString(gameboard.getTurnCount()));
                        buttonArray[i][j].setEnabled(false);
                        gameboard.tableData[i][j] = "Information Set.";
                    }
                    if (gameboard.tableData[i][j].equals("Player 2's Space")) {
                        buttonArray[i][j].setBackground(Color.RED);
                        buttonArray[i][j].setText(Integer.toString(gameboard.getTurnCount()));
                        buttonArray[i][j].setEnabled(false);
                        gameboard.tableData[i][j] = "Information Set.";
                    }
                }
            }
            
            //Updates the available editable buttons for each turn.
            if (gameboard.isPlayer2Turn()) {
                for (int k = 0; k < gameboard.getRowCount(); k++) {
                    for (int l = 0; l < gameboard.getColumnCount(); l++) {
                        buttonArray[k][l].setEnabled(false);
                    }
                }
                if (gameboard.space1 == true) {
                    buttonArray[gameboard.getAvailableRow1()][gameboard.getAvailableColumn1()].setEnabled(true);
                }
                if (gameboard.space2 == true) {
                    buttonArray[gameboard.getAvailableRow2()][gameboard.getAvailableColumn2()].setEnabled(true);
                }
                if (gameboard.space3 == true) {
                    buttonArray[gameboard.getAvailableRow3()][gameboard.getAvailableColumn3()].setEnabled(true);
                }
                if (gameboard.space4 == true) {
                    buttonArray[gameboard.getAvailableRow4()][gameboard.getAvailableColumn4()].setEnabled(true);
                }
            } else {
                for (int i = 0; i < gameboard.getRowCount(); i++) {
                    for (int j = 0; j < gameboard.getColumnCount(); j++) {
                        if (gameboard.tableData[i][j].equals("")) {
                            buttonArray[i][j].setEnabled(true);
                        }
                    }
                }
            }

            //Winning Conditions 
            if ((gameboard.player1Victory == true) || (gameboard.getTurnCount() == gameboard.getTotalCellCount() && gameboard.player2Turn == true)) {
                JOptionPane.showMessageDialog(null, "Player 1 Wins!");
                scoreChecker();
                gameboard.player1Score = gameboard.player1Score + tempScoreCounter;
                gameboard.ui.setPlayer1Score(gameboard.player1Score);
                tempScoreCounter = 0;
                createBoard();
            }
            if (gameboard.getTurnCount() == gameboard.getTotalCellCount() && gameboard.player2Turn == false) {
                JOptionPane.showMessageDialog(null, "Player 2 Wins!");
                scoreChecker();
                gameboard.player2Score = gameboard.player2Score + tempScoreCounter;
                gameboard.ui.setPlayer2Score(gameboard.player2Score);
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
            for (int i = 0; i < matchgameboard.hiScores.size(); i++) {
                hs = matchgameboard.hiScores.get(i);
                String s = ((i + 1) + "    " + hs.toString());
                JTextField tf = new JTextField(s);
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setEditable(false);
                tf.setFont(font1);
                tf.setSize(150,50);
                panel.add(tf);
                dialog.add(panel);
            }
            dialog.setVisible(true);
        }

    }

}
