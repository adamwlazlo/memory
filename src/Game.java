import java.util.*;

public class Game {

    private ArrayList<String> drawnWords;
    private ArrayList<String> shuffleWords;
    private ArrayList<Integer> guessedFirstRow;
    private ArrayList<Integer> guessedSecondRow;
    private int numberOfWords;
    private int round;

    public Game() {
        int level = SelectLevel();
        drawnWords = new ArrayList<>(); //first row
        shuffleWords = new ArrayList<>(); //second row
        guessedFirstRow = new ArrayList<>();
        guessedSecondRow = new ArrayList<>();
        if (level == 1) {
            numberOfWords = 4;
            round = 10;
        } else {
            numberOfWords = 8;
            round = 15;
        }
    }

    private void InitializeGame() {
        Word word = new Word();
        drawnWords = word.DrawWords(numberOfWords);
        shuffleWords = new ArrayList<>(drawnWords);
        Collections.shuffle(shuffleWords);

        for (int i = 0; i < numberOfWords; i++) {
            guessedFirstRow.add(0);
            guessedSecondRow.add(0);
        }

        System.out.println(drawnWords);
        System.out.println(shuffleWords);
        System.out.println("\nYou have " + round + " round.");

    }

    public int SelectLevel() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Chose level:\n1 - four word; 10 rounds;\n2 - eight words; 15 rounds\n\n");

        return sc.nextInt();
    }

    public void Play() {
        long startTime = System.currentTimeMillis();
        int guessedWords = 0;
        int roundNum = 0;
        InitializeGame();

        while (guessedWords < numberOfWords && roundNum < round) {
            PrintGuessedWords(guessedFirstRow, drawnWords);
            PrintGuessedWords(guessedSecondRow, shuffleWords);
            System.out.printf("\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n");
            System.out.println("\nRound " + (roundNum + 1));

            System.out.print("Select the index of word to display in firs row\n");

            int firstDigit = sc.nextInt();
            if (firstDigit < 1 || firstDigit > numberOfWords) {
                System.out.println("Enter a number from 1 to " + numberOfWords);
                continue;
            }

            PrintGivenWords(drawnWords, firstDigit);

            System.out.printf("\n");
            System.out.print("Select the index of word to display in second row\n");
            int secondDigit = sc.nextInt();

            if (secondDigit < 1 || secondDigit > numberOfWords) {
                System.out.println("Enter a number from 1 to " + numberOfWords);
                continue;
            }

            PrintGivenWords(shuffleWords, secondDigit);

            if (drawnWords.get(firstDigit - 1) == shuffleWords.get(secondDigit - 1)) {
                guessedWords++;
                guessedFirstRow.set(firstDigit - 1, 1);
                guessedSecondRow.set(secondDigit - 1, 1);
            } else {
                System.out.println("\n\nGiven words doesn't match");
            }

            roundNum++;
        }
        if (guessedWords == numberOfWords) {
            System.out.println("WIN!");
            System.out.println("You solved the game after " + roundNum + " rounds.");

        } else
            System.out.println("LOOSE!");

        long stopTime = System.currentTimeMillis();
        System.out.println("It took you " +( (stopTime - startTime) / 1000) + " seconds.");

    }

    public static boolean RestartQuestion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want play again? \ny - yes;\nany other - no;\n\n");

        char answer = sc.next().charAt(0);
        return answer == 'y';
    }

    private void PrintGuessedWords(ArrayList<Integer> guessedRow, ArrayList<String> drawnWords) {
        String displayWords = "";

        for (int i = 0; i < numberOfWords; i++) {
            if (guessedRow.get(i) == 0) {
                displayWords = displayWords + "    " + "X";
            } else {
                displayWords = displayWords + "    " + drawnWords.get(i);
            }
        }
        System.out.println(displayWords);
    }

    private void PrintGivenWords(ArrayList<String> drawnWords, int index) {
        for (int i = 0; i < numberOfWords; i++) {
            if (i != index - 1)
                System.out.printf(" X ");
            else
                System.out.printf(drawnWords.get(i));
        }

        System.out.printf("\n\n");
    }

}
