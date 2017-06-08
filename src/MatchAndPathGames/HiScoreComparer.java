/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.util.Comparator;

/**
 * @author Jon
 * Used to sort the collection of high scores into the correct order
 * (descending order)
 */
public class HiScoreComparer implements Comparator<HiScore> {

    @Override
    public int compare(HiScore score1, HiScore score2) {

        int s1 = score1.getScore();
        int s2 = score2.getScore();

        if (s1 > s2) {
            return -1;
        } else if (s1 < s2) {
            return +1;
        } else {
            return 0;
        }
    }
}
