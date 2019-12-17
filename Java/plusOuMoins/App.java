package com.cesi;

import com.cesi.Game;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Game plusOuMoins = new Game();
        plusOuMoins.startGame();
        System.out.println("Bye World!");
    }
}
