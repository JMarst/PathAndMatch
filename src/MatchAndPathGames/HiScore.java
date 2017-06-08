/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatchAndPathGames;

import java.io.Serializable;

/**
 *
 * @author Jon
 * Used to store each high score value as a name and score
 */
public class HiScore implements Serializable {

    private final String name;
    private final int score;

    public HiScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * 
     * @return the String
     */
    @Override
    public String toString() {
        return name + ":      " + score;
    }

}
