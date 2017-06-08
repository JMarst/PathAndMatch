/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Jon
 */
public class GameFrame extends JFrame
        implements GameGUI {

    String player1Name;
    String player2Name;
    Font font1;
    JButton[][] buttonArray;
    JTextField player1ScoreField;
    JTextField player2ScoreField;
    JPanel spacePanel;
    LineBorder turnBorder;
    JLabel player1, player2;
    JButton player1Option, player2Option;
    int tempScoreCounter;
    GameBoard gameboard;
    boolean player1IsCPU;
    boolean player2IsCPU;

    public GameFrame(GameBoard gameboard, String gameType) {
        super(gameType);
        this.gameboard = gameboard;
        font1 = new Font("SansSerif", Font.BOLD, 20);
        createInfoBox();
        turnBorder = new LineBorder(Color.YELLOW, 3, true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Component createInfoBox() {
        JPanel outerBorder, playerInfoBox;
       
        outerBorder = (JPanel) getContentPane();
        outerBorder.setLayout(new BorderLayout(3, 3));
        outerBorder.setBorder(new LineBorder(Color.RED, 2, true));

        spacePanel = new JPanel();
        spacePanel.setLayout(new BorderLayout(3, 3));
        spacePanel.setBorder(new EmptyBorder(4, 4, 4, 4));
        outerBorder.add(spacePanel, BorderLayout.CENTER);

        playerInfoBox = new JPanel();
        playerInfoBox.setLayout(new GridLayout(2, 2, 2, 7));
        playerInfoBox.setName("Player Info");
        LineBorder lb = new LineBorder(Color.BLUE, 2, true);
        TitledBorder titled = new TitledBorder(lb, "Player Info");
        playerInfoBox.setBorder(titled);

        player1 = new JLabel("" + player1Name);
        player1.setFont(font1);
        player1.setBackground(Color.GREEN);
        player1.setOpaque(true);
        playerInfoBox.add(player1);

        player1ScoreField = new JTextField("0");
        player1ScoreField.setFont(font1);
        player1ScoreField.setBorder(new LineBorder(Color.GRAY, 1, true));
        player1ScoreField.setEditable(false);
        playerInfoBox.add(player1ScoreField);
        
        player1Option = new JButton(new Player1OptionAction());
        player1Option.setText("Change Player 1");
        playerInfoBox.add(player1Option);

        player2 = new JLabel("" + player2Name);
        player2.setFont(font1);
        player2.setBackground(Color.RED);
        player2.setOpaque(true);
        playerInfoBox.add(player2);

        player2ScoreField = new JTextField("0");
        player2ScoreField.setFont(font1);
        player2ScoreField.setBorder(new LineBorder(Color.GRAY, 1, true));
        player2ScoreField.setEditable(false);
        playerInfoBox.add(player2ScoreField);
               
        player2Option = new JButton(new Player2OptionAction());
        player2Option.setText("Change Player 2");
        playerInfoBox.add(player2Option);
              
        spacePanel.add(playerInfoBox, BorderLayout.NORTH);
        
        //Border used to show whose turn it is. Starts off on player 1's turn.
        player1.setBorder(turnBorder);

        return spacePanel;
    }
   
    //Works out the score for a particular game by counting the empty spaces.
    void scoreChecker() {
        for (int i = 0; i < gameboard.getRowCount(); i++) {
                for (int j = 0; j < gameboard.getColumnCount(); j++) {
                    if (gameboard.tableData[i][j].equals("")) {
                        tempScoreCounter++;
                    }
                }
            }
    }
    
    //Updates the border to show whose turn it is.
    void turnUpdate() {
            if(gameboard.player2Turn) {
                player2.setBorder(turnBorder);
                player1.setBorder(null);
            }
            else {
                player1.setBorder(turnBorder);
                player2.setBorder(null);
            }      
        }
    
    /**
     * Used to change player 1 during game play. Only used by the MatchGame.
     */
    void changePlayer1() {
        gameboard.addScore(player1Name, gameboard.player1Score);
        gameboard.addScore(player2Name, gameboard.player2Score);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String y = (String) JOptionPane.showInputDialog(
                this,
                "Player 1 Name: ",
                "Customized Dialog",
                JOptionPane.QUESTION_MESSAGE);
        player1Name = y;
        if(player1Name == null) {
            player1Name = "?";
        }
        player1.setText("" + player1Name);
        gameboard.player1Score = 0;
        gameboard.ui.setPlayer1Score(gameboard.player1Score);
        gameboard.player2Score = 0;
        gameboard.ui.setPlayer2Score(gameboard.player2Score);
    }
    
    /**
     * Used to change player 2 during game play. Only used by the MatchGame.
     */
    void changePlayer2() {
        gameboard.addScore(player1Name, gameboard.player1Score);
        gameboard.addScore(player2Name, gameboard.player2Score);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String z = (String) JOptionPane.showInputDialog(
                this,
                "Player 2 Name: ",
                "Customized Dialog",
                JOptionPane.QUESTION_MESSAGE);
        player2Name = z;
        if(player2Name == null) {
            player2Name = "??";
        }
        player2.setText("" + player2Name);
        gameboard.player1Score = 0;
        gameboard.ui.setPlayer1Score(gameboard.player1Score);
        gameboard.player2Score = 0;
        gameboard.ui.setPlayer2Score(gameboard.player2Score);
    }
    
    //Action that listens when the 'change player 1' button is pressed
    class Player1OptionAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            changePlayer1();
        }
    }
    
    //Action that listens when the 'change player 2' button is pressed
    class Player2OptionAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            changePlayer2();
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
    }

    @Override
    public void createWarning(String info, String heading) {
        JOptionPane.showMessageDialog(this,
		info,
		heading,
		JOptionPane.WARNING_MESSAGE);
    }

}
