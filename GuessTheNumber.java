import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumber {

    public static void main(String[] args) {
        // set the range for the generated number
        int minNumber = 1;
        int maxNumber = 100;

        // set the maximum number of attempts
        int maxAttempts = 5;

        // set the number of rounds
        int numRounds = 2;

        // set the initial score to 0
        int score = 0;

        for (int round = 1; round <= numRounds; round++) {
            // generate a random number between minNumber and maxNumber
            Random random = new Random();
            int number = random.nextInt(maxNumber - minNumber + 1) + minNumber;

            int guess = 0;
            int attempts = 0;

            while (guess != number && attempts < maxAttempts) {
                // ask the user to enter a number
                String input = JOptionPane.showInputDialog(null,
                        "Round " + round + ": Guess the number between " + minNumber + " and " + maxNumber + " (Attempts left: " + (maxAttempts - attempts) + "):",
                        "Guess The Number Game",
                        JOptionPane.QUESTION_MESSAGE);

                // check if the user cancels the input dialog
                if (input == null) {
                    // exit the game
                    System.exit(0);
                }

                // parse the input as an integer
                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    // show an error message if the input is not a valid integer
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid integer.",
                            "Guess The Number Game",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // check if the guess is within the given range
                if (guess < minNumber || guess > maxNumber) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a number between " + minNumber + " and " + maxNumber + ".",
                            "Guess The Number Game",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                attempts++;

                // compare the guess with the number
                if (guess < number) {
                    JOptionPane.showMessageDialog(null,
                            "Your guess is too low.",
                            "Guess The Number Game",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (guess > number) {
                    JOptionPane.showMessageDialog(null,
                            "Your guess is too high.",
                            "Guess The Number Game",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (guess == number) {
                // calculate the score based on the number of attempts
                int roundScore = (maxAttempts - attempts + 1) * 10;

                // add the round score to the total score
                score += roundScore;

                JOptionPane.showMessageDialog(null,
                        "Congratulations! You guessed the number " + number
                                + " in " + attempts + " attempts.\n"
                                + "You scored " + roundScore + " points in this round.",
                        "Guess The Number Game",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Sorry, you ran out of attempts. The number was " + number + ".",
                        "Guess The Number Game",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null,
                "Game over! Your total score is " + score + " points.",
                "Guess The Number Game",
                JOptionPane.INFORMATION_MESSAGE);
    }
}