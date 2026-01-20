import java.util.Map;
import java.util.Random;

public class Slots {

    /** Creating a player which has his own economy. */
    private static Economy player = new Economy();

    /**
     * Main method, starts the application.
     * @param args
     */
    public static void main(final String[] args) {

        /* Do-while loop, which holds the spinning going */
        do {
            // Generating the symbols for each roll.
            final String[][] slotSymbols = generateSlotSymbols();
            if (player.spinAndReduceBalance()) {
                // Displaying the array of symbols.
                displaySlots(slotSymbols);
            } else {
                query("Out of money...");
            }
            countWinAmount(detectWins(slotSymbols));
            IO.println("Balance - " + player.currentBalance());
            query("Press enter to spin again...");
        } while (player.currentBalance() > player.getcostToSpin());
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
        final int index4 = 3;
        final int index5 = 4;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0].equals(arr[i][1]) && (arr[i][1].equals(arr[i][2]))) {
                winningLines[i] = arr[i][i];
            }
        }
        // Check the diagonal wins - Top to bottom
        if (arr[0][0].equals(arr[1][1]) && (arr[1][1].equals(arr[2][2]))) {
            IO.println("Top-left to down-right diagonal win!");
            winningLines[index4] = arr[0][0];
        }
        // Bottom to top
        if (arr[2][0].equals(arr[1][1]) && (arr[1][1].equals(arr[0][2]))) {
            IO.println("Bottom-left to up-right diagonal win!");
            winningLines[index5] = arr[2][0];
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
            switch (s) {
                case "🍒" -> player.adjustBalance(symbolPayout("🍒"));
                case "🍇" -> player.adjustBalance(symbolPayout("🍇"));
                case "🍋" -> player.adjustBalance(symbolPayout("🍋"));
                case "🍌" -> player.adjustBalance(symbolPayout("🍌"));
                case "🍉" -> player.adjustBalance(symbolPayout("🍉"));
                case "🍓" -> player.adjustBalance(symbolPayout("🍓"));
                case "🌟" -> player.adjustBalance(symbolPayout("🌟"));
                case "🎰" -> player.adjustBalance(symbolPayout("🎰"));
                case "💎" -> player.adjustBalance(symbolPayout("💎"));
                default -> IO.print("");
            }
        }
    }

    /**
     * Prints the string given as a param.
     * @param input the string to be printed.
     * @return IO.readln();
     */
    public static String query(final String input) {
        IO.print(input);
        return IO.readln();
    }

    /** Map which has the symbols, and their payout values. */
    private static final Map<String, Double> VALUES = Map.of(
            "🍒", 2.0,
            "🍇", 3.5,
            "🍋", 5.0,
            "🍌", 7.5,
            "🍉", 10.0,
            "🍓", 12.5,
            "🌟", 15.0,
            "🎰", 17.5,
            "💎", 20.0);

    /**
     * This method utilizes the map above to payout wins.
     * @param symbol the winning symbol.
     * @return the profit earned from a symbol.
     */
    public static double symbolPayout(final String symbol) {
        double profit = VALUES.getOrDefault(symbol, 0.0);
        IO.println(symbol + ": Pays out: " + profit);
        return profit;
    }
}

class Economy {
    /** Starting balance. */
    private final double startingBalance = 100;
    /** Users balance. */
    private double balance = startingBalance;
    /** Cost to spin the slot. */
    private final double costToSpin = 5;
    /** Constructor. */
    Economy() {
    }
    /** Get the amount required for a spin.
     * @return the cost of a single spin. */
    public double getcostToSpin() {
        return this.costToSpin;
    }

    /** Display the players balance.
     * @return this.balance. */
    public double currentBalance() {
        return this.balance;
    }

    public void adjustBalance(final double change) {
        this.balance += change;
    }

    public boolean spinAndReduceBalance() {
        if (this.balance >= costToSpin) {
            adjustBalance(-costToSpin);
            return true;
        }
        return false;
    }
}
