package com.princevelasco.FlashCardGame;

import java.util.Locale;

import static com.princevelasco.FlashCardGame.Cards.flashcards;
import static com.princevelasco.FlashCardGame.utils.Map.getKeysByValue;

/**
 * com.princevelasco.FlashCardGame.Question class for storing the term and its definition.
 *
 * @param term       the term of the question.
 * @param definition the definition of the question.
 */
record Question(String term, String definition) {

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
    void isCorrect(String answer) {
        if (answer.toLowerCase(Locale.ROOT).trim().equals(definition)) {
            System.out.println("Your answer is right!");
        } else {
            if (flashcards.containsValue(answer)) {
                String otherTermCorrect = getKeysByValue(flashcards, answer);
                System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".\n", definition, otherTermCorrect);
            } else {
                System.out.printf("Wrong. The right answer is \"%s\".\n", definition);
            }
        }
    }
}
