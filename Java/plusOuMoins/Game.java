package com.cesi;

import java.util.Random;

public class Game {
    int numberToFind;
    int tries;
    boolean found;
    int maxLimit = 100;

    public Game() {
        this.found = false;
        this.tries = 0;
    }

    public Game(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void startGame() {
        Random r = new Random();
        numberToFind = r.nextInt(101);
        System.out.println("number = " + numberToFind);
    }
}