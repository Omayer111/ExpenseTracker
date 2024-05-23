package com.example.expensetracker.Model;

/**
 * Represents an account with a name and an amount.
 */
public class Account {

    private double accountAmount;
    private String accountName;

    /**
     * Default constructor for Account.
     */
    public Account() {
    }

    /**
     * Constructs a new Account with the specified amount and name.
     *
     * @param accountAmount The amount in the account.
     * @param accountName The name of the account.
     */
    public Account(double accountAmount, String accountName) {
        this.accountAmount = accountAmount;
        this.accountName = accountName;
    }

    /**
     * Returns the amount in the account.
     *
     * @return The account amount.
     */
    public double getAccountAmount() {
        return accountAmount;
    }

    /**
     * Sets the amount in the account.
     *
     * @param accountAmount The new account amount.
     */
    public void setAccountAmount(double accountAmount) {
        this.accountAmount = accountAmount;
    }

    /**
     * Returns the name of the account.
     *
     * @return The account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the name of the account.
     *
     * @param accountName The new account name.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}