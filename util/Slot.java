package util;

public class Slot {
    /** Starting balance. */
    private final double startingBalance = 50;

    /** Users balance. */
    private double balance = startingBalance;

    /** Bet amount. */
    private final double bet = 1.5;

    /** Constructor. */
    public Slot() {
    }

    /** Display the players balance.
     * @return this.balance. */
    public double currentBalance() {
        return this.balance;
    }

    /** Display the bet.
     * @return this.bet. */
    public double getBet() {
        return this.bet;
    }

    /** Adjust balance.
     * @param change amount that will be used for adjusting.
     */
    public void adjustBalance(final double change) {
        this.balance += change;
    }

    /** Reduces balance by the amount of a spin. */
    public void spin() {
        adjustBalance(-bet);
    }
}
