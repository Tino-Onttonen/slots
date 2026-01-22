package util;

public class Economy {
    /** Starting balance. */
    private final double startingBalance = 100;
    /** Users balance. */
    private double balance = startingBalance;
    /** Constructor. */
    public Economy() {
    }
    /** Display the players balance.
     * @return this.balance. */
    public double currentBalance() {
        return this.balance;
    }

    public void adjustBalance(final double change) {
        this.balance += change;
    }

    public boolean spinIfMoneyLeft() {
        if (this.balance >= src.Slots.BET) {
            adjustBalance(-src.Slots.BET);
            return true;
        }
        return false;
    }
}
