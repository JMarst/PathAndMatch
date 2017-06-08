/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.util.List;

/**
 *
 * @author Jon
 */
public interface GameGUI {
    public void setPlayer1Score(int n);
    public void setPlayer2Score(int n);
    public void showHighScores(List<HiScore> hiScores);

    public void createWarning(String Cannot_read_HiScores, String Cannot_load_HiScores);
}
