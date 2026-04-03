package src;
import util.Gui;
import util.Slot;
import java.util.Map;
import java.util.Random;


public class Slots {
    /** Slot-object for betting. */
    private static Slot slot = new Slot();

    /** Map which has every possible symbol, and their payout values. */
    private static final Map<String, Double> VALUES = Map.of(
            "🍒", 5.0,
            "🍇", 7.5,
            "🍋", 10.0,
            "🍌", 12.5,
            "🍉", 15.0,
            "🍓", 17.5,
            "🌟", 20.0,
            "🎰", 22.5,
            "💎", 25.0);

    /** Starting point of program -> starts GUI & initializes gameLoop.
     * @param args
     */
    public static void main(final String[] args) {
        Gui.invoke();
    }


    public static double balance() {
        return slot.currentBalance();
    }
    public static double bet() {
        return slot.getBet();
    }
    public static void changeBetAmount(int bet) {
        slot.setBet(bet);
    }

    /** Starts a loop, in which the player can play until out of balance.
     * @return
     */
    public static String[][] gameLoop() {
        String[][] temp = returnSpinResult();
        slot.reduceBalance();
        payoutWins(detectWins(temp));
        return temp;
    }

    /** Generates a 3x3 array of winning icons.
     * Possible icons are listed in symbols array.
     * @return array[3][3] - which has the winning icons.
     */
    public static String[][] returnSpinResult() {
        final int size = 3;
        String[][] symbols = new String[size][size];
        String[] temp = VALUES.keySet().toArray(new String[size * size]);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int rndIndex = new Random().nextInt(temp.length);
                symbols[i][j] = temp[rndIndex];
            }
        }
        return symbols;
    }

    /** Detects wins in a row, or diagonally.
     * @param arr has the symbols randomly generated each spin.
     * @return the first symbol(s) on each line where a win was detected,
     * as an array.
     */
    public static String[] detectWins(final String[][] arr) {
        String[] winningLines = {"", "", "", "", ""};
        final int diagonalTop = 3;
        final int diagonalBottom = 4;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0].equals(arr[i][1]) && (arr[i][1].equals(arr[i][2]))) {
                winningLines[i] = arr[i][i];
            }
        }
        if (arr[0][0].equals(arr[1][1]) && (arr[1][1].equals(arr[2][2]))) {
            winningLines[diagonalTop] = arr[0][0];
        }
        // Bottom to top
        if (arr[2][0].equals(arr[1][1]) && (arr[1][1].equals(arr[0][2]))) {
            winningLines[diagonalBottom] = arr[2][0];
        }
        return winningLines;
    }

    /** Counts the gained amount of balance, which will then be adjusted.
     * Calls the adjusting method to modify balance.
     * @param symbol the symbols of the spin given as a parameter.
     */
    public static void payoutWins(final String[] symbol) {
        for (String winSymbol : symbol) {
            slot.adjustBalance(calculateWins(winSymbol));
        }
    }

    /** Connects the mapped symbol and its value to return payout values.
     * @param winSymbol the winning symbol.
     * @return the profit earned from a symbol multiplied by bet.
     */
    public static double calculateWins(final String winSymbol) {
        double profit = VALUES.getOrDefault(winSymbol, 0.0) * bet();
        if (!(profit == 0.0)) {
            IO.println(winSymbol + ": Pays out: " + profit);
        }
        return profit;
    }
}
