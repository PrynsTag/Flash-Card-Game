package com.princevelasco.FlashCardGame;

import java.io.IOException;

/**
 * This class is the main class of the program. It contains the main method.
 *
 * @author Prince Carl Velasco
 * @version 1.0
 * @since 2022-02-24
 */
class FlashCardGame {

    /**
     * This is the main runner of the program. It contains the start method.
     */
    void start() {
        Cards cards = new Cards();

        cards.inputCards();
        cards.play();
    }


    /**
     * This method clears the screen.
     */
    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec(new String[]{"clear"});
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * This is the string representation of the title of the program.
     *
     * @return The title of the game.
     */
    public String toString() {
        return "com.princevelasco.FlashCardGame.FlashCardGame";
    }
}
