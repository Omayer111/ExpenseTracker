package com.example.expensetracker.Model;

/**
 * Represents an income transaction with details such as category, account, note, date, amount, and ID.
 */
public class Income implements Transaction {

    private String category;
    private String account;
    private String note;
    private String date;
    private double amount;
    private long id;

    /**
     * Constructs a new Income with the specified details.
     *
     * @param category The category of the income.
     * @param account The account associated with the income.
     * @param note A note or description for the income.
     * @param date The date of the income.
     * @param amount The amount of the income.
     * @param id The unique identifier for the income.
     */
    public Income(String category, String account, String note, String date, double amount, long id) {
        this.category = category;
        this.account = account;
        this.note = note;
        this.date = date;
        this.amount = amount;
        this.id = id;
    }

    /**
     * Returns the type of the transaction, which is "Income".
     *
     * @return The type of the transaction.
     */
    @Override
    public String getType() {
        return "Income";
    }

    /**
     * Returns the category of the income.
     *
     * @return The category of the income.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the account associated with the income.
     *
     * @return The account associated with the income.
     */
    @Override
    public String getAccount() {
        return account;
    }

    /**
     * Returns the note or description of the income.
     *
     * @return The note or description of the income.
     */
    @Override
    public String getNote() {
        return note;
    }

    /**
     * Returns the date of the income.
     *
     * @return The date of the income.
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * Returns the amount of the income.
     *
     * @return The amount of the income.
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the unique identifier of the income.
     *
     * @return The unique identifier of the income.
     */
    @Override
    public long getId() {
        return id;
    }
}