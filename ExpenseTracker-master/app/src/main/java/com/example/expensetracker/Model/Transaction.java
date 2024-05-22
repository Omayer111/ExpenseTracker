package com.example.expensetracker.Model;

/**
 * The Transaction interface represents a financial transaction with basic details such as type, category, account,
 * note, date, amount, and ID.
 */
public interface Transaction {

    /**
     * Returns the type of the transaction.
     *
     * @return The type of the transaction.
     */
    String getType();

    /**
     * Returns the category of the transaction.
     *
     * @return The category of the transaction.
     */
    String getCategory();

    /**
     * Returns the account associated with the transaction.
     *
     * @return The account associated with the transaction.
     */
    String getAccount();

    /**
     * Returns the note or description of the transaction.
     *
     * @return The note or description of the transaction.
     */
    String getNote();

    /**
     * Returns the date of the transaction.
     *
     * @return The date of the transaction.
     */
    String getDate();

    /**
     * Returns the amount of the transaction.
     *
     * @return The amount of the transaction.
     */
    double getAmount();

    /**
     * Returns the unique identifier of the transaction.
     *
     * @return The unique identifier of the transaction.
     */
    long getId();
}
