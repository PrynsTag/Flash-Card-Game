package com.princevelasco.FlashCardGame;

import java.util.Locale;

/**
 * com.princevelasco.FlashCardGame.Question class for storing the term and its definition.
 *
 * @param term       the term of the question.
 * @param definition the definition of the question.
 */
public record Question(String term, String definition) {

    /**
     * Returns the term of the question.
     *
     * @return the term of the question.
     */
    public String getTerm() {
        return term;
    }

    /**
     * Returns the definition of the question.
     *
     * @return the definition of the question.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Returns the if the question is answered correctly.
     *
     * @param answer the answer given by the user.
     *               The answer is considered correct if it is equal to the definition of the question.
     *               The answer is considered incorrect if it is not equal to the definition of the question.
     *               (case-insensitive)
     *               (trimmed)
     */
    public void isCorrect(String answer) {
        if (answer.toLowerCase(Locale.ROOT).trim().equals(definition)) {
            System.out.println("Your answer is right!");
        } else {
            System.out.println("Your answer is wrong...");
        }
    }
}
