/**
 * The `ATMUser` class represents a user account in the ATM system.
 * It contains information such as user ID, card number, PIN, balance, and maintaining balance.
 */
public class ATMUser {
    private int id;             // The unique identifier for the user.
    private String cardNumber;  // The card number associated with the user.
    private String pin;         // The Personal Identification Number (PIN) for user authentication.
    private double balance;     // The current balance in the user's account.
    private double maintainingBalance; // The maintaining balance for the user's account.

    // Constructor for the ATMUser
    public ATMUser(int id, String cardNumber, String pin, double balance, double maintainingBalance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.maintainingBalance = maintainingBalance;
    }

    /**
     * Gets the user's unique identifier.
     *
     * @return The user's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the card number associated with the user.
     *
     * @return The card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Gets the Personal Identification Number (PIN) for user authentication.
     *
     * @return The user's PIN.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Gets the current balance in the user's account.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance in the user's account.
     *
     * @param balance The new balance to set.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the maintaining balance for the user's account.
     *
     * @return The maintaining balance.
     */
    public double getMaintainingBalance() {
        return maintainingBalance;
    }
}
