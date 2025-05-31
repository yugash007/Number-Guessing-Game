import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;

        System.out.println("*************************************");
        System.out.println("* Welcome to the Number Guessing Game! *");
        System.out.println("*************************************");

        while (playAgain) {
            roundsPlayed++;
            int lowerBound = 1;
            int upperBound = 100;
            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int maxAttempts = 10; // You can adjust this
            int attemptsMade = 0;
            boolean guessedCorrectly = false;
            int roundScore = 0;

            System.out.println("\n--- Round " + roundsPlayed + " ---");
            System.out.println("I've picked a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attemptsMade < maxAttempts && !guessedCorrectly) {
                System.out.print("\nEnter your guess (Attempt " + (attemptsMade + 1) + "/" + maxAttempts + "): ");
                try {
                    int playerGuess = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    attemptsMade++;

                    if (playerGuess < lowerBound || playerGuess > upperBound) {
                        System.out.println("Oops! Your guess is outside the valid range (" + lowerBound + "-" + upperBound + ").");
                    } else if (playerGuess < numberToGuess) {
                        System.out.println("Too low! Try a higher number.");
                    } else if (playerGuess > numberToGuess) {
                        System.out.println("Too high! Try a lower number.");
                    } else {
                        guessedCorrectly = true;
                        System.out.println("🎉 Congratulations! You guessed the number " + numberToGuess + " correctly!");
                        // Calculate score based on attempts
                        roundScore = (maxAttempts - attemptsMade + 1) * 10; // Higher score for fewer attempts
                        System.out.println("You took " + attemptsMade + " attempts. Your score for this round: " + roundScore);
                        totalScore += roundScore;
                    }

                } catch (InputMismatchException e) {
                    System.out.println(" Invalid input! Please enter a whole number.");
                    scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
                    // Optionally, you could choose not to count this as an attempt:
                    // attemptsMade--; // Uncomment if you don't want invalid input to count as an attempt
                }

                if (!guessedCorrectly && attemptsMade < maxAttempts) {
                    System.out.println("Attempts remaining: " + (maxAttempts - attemptsMade));
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\n😥 Game Over! You ran out of attempts.");
                System.out.println("The number I was thinking of was: " + numberToGuess);
                System.out.println("Your score for this round: 0");
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\n--- Game Summary ---");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Total score: " + totalScore);
        System.out.println("Thanks for playing! Goodbye. 👋");

        scanner.close();
    }
}