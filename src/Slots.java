package src;
import java.util.Map;
import java.util.Random;


import javax.swing.SwingUtilities;

import util.Gui;

public class Slots {

    /** Creating a player which has his own economy. */
    private static util.Economy player = new util.Economy();
    /** Price to roll the slots. */
    public static final double BET = 5.0;
    /** Highscore of the session. */
    private static double highscore = 0;


    /**
     * Main method, starts the application.
     * @param args
     */
    public static void main(final String[] args) {
        // Thread-safe initialization of GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui gui = new Gui();
                gui.initialize();

            }
        });
        /* Do-while loop, which holds the spinning going */
        do {
            // Generating the symbols for each roll.
            final String[][] slotSymbols = generateSlotSymbols();
            if (player.spinIfMoneyLeft()) {
                // Displaying the array of symbols.
                displaySlots(slotSymbols);
            } else {
                query("Out of money...");
            }
            countWinAmount(detectWins(slotSymbols));
            IO.println("Balance - " + player.currentBalance());
            query("Press enter to spin again...");
            if (player.currentBalance() > highscore) {
                highscore = player.currentBalance();
            }
            IO.println("Your current highest cash value is/was: " +  highscore);
        } while (player.currentBalance() > BET);
    }

    /**
     * Generates a 3x3 array of winning icons.
     * Possible icons are "🍒", "🍇", "🍋","🍌", "🍉", "🍎","🍓", "🌟", "💎".
     * @return array[3][3] - which has the winning icons.
     */
    public static String[][] generateSlotSymbols() {
        //Possible symbols
        String[][] arr = {{"🍒", "🍇", "🍋"},
                          {"🍌", "🍉", "🍓"},
                          {"🌟", "🎰", "💎"}};

        //Empty array which will hold winning icons
        String[][] winningSymbols = new String[arr.length][arr.length];

        //Filling the winning array with icons
        for (int i = 0; i < arr.length; i++) {
            int rndIndex1 = new Random().nextInt(arr.length);
            for (int j = 0; j < arr.length; j++) {
                int rndIndex2 = new Random().nextInt(arr.length);
                winningSymbols[i][j] = arr[rndIndex1][rndIndex2];
            }
        }
        return winningSymbols;
    }


    /** Prints the rounds icons.
     * @param arr given as argument,
     * contains the randomly generated symbols per spin.
     */
    public static void displaySlots(final String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                IO.print(arr[i][j]);
            }
            IO.println();
        }
    }

    /**
     * Detects wins in a row, or diagonally.
     * @param arr given as argument,
     * contains the randomly generated symbols per spin.
     * @return string that has the winning symbols.
     */
    public static String[] detectWins(final String[][] arr) {
        String[] winningLines = {"", "", "", "", ""};
        //Check if the user gets a win on lines 1-3
        final int diagonalTop = 3;
        final int diagonalBottom = 4;

        // Checks the top-bottom rows for eligible wins
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0].equals(arr[i][1]) && (arr[i][1].equals(arr[i][2]))) {
                winningLines[i] = arr[i][i];
            }
        }
        // Check the diagonal wins - Top to bottom
        if (arr[0][0].equals(arr[1][1]) && (arr[1][1].equals(arr[2][2]))) {
            winningLines[diagonalTop] = arr[0][0];
        }
        // Bottom to top
        if (arr[2][0].equals(arr[1][1]) && (arr[1][1].equals(arr[0][2]))) {
            winningLines[diagonalBottom] = arr[2][0];
        }
        return winningLines;
    }

    /**
     * Counts the gained amount of balance, which will then be adjusted.
     * Calls the adjusting method to modify balance.
     * @param symbol the symbols of the spin given as a parameter.
     */
    public static void countWinAmount(final String[] symbol) {
        for (String s : symbol) {
            player.adjustBalance(symbolPayout(s));
        }
    }

    /**
     * Prints the string given as a param.
     * @param input the string to be printed.
     * @return readline */
    public static String query(final String input) {
        IO.print(input);
        return IO.readln();
    }

    /**
     * This method utilizes the map above to payout wins.
     * @param symbol the winning symbol.
     * @return the profit earned from a symbol.
     */
    public static double symbolPayout(final String symbol) {
    // Map which has the symbols, and their payout values.
        final Map<String, Double> values = Map.of(
                "🍒", 2.0,
                "🍇", 3.5,
                "🍋", 5.0,
                "🍌", 7.5,
                "🍉", 10.0,
                "🍓", 12.5,
                "🌟", 15.0,
                "🎰", 17.5,
                "💎", 20.0);

        double profit = values.getOrDefault(symbol, 0.0);
        if (!(profit == 0.0)) {
            IO.println(symbol + ": Pays out: " + profit);
        }
        return profit;
    }
}


