package com.example.expensetracker.Model;

/**
 * Represents an expense transaction with details such as category, account, note, date, amount, and ID.
 */
public class Expense implements Transaction {

    private String category;
    private String account;
    private String note;
    private String date;
    private double amount;
    private long id;

    /**
     * Constructs a new Expense with the specified details.
     *
     * @param category The category of the expense.
     * @param account The account associated with the expense.
     * @param note A note or description for the expense.
     * @param date The date of the expense.
     * @param amount The amount of the expense.
     * @param id The unique identifier for the expense.
     */
    public Expense(String category, String account, String note, String date, double amount, long id) {
        this.category = category;
        this.account = account;
        this.note = note;
        this.date = date;
        this.amount = amount;
        this.id = id;
    }

    /**
     * Returns the type of the transaction, which is "Expense".
     *
     * @return The type of the transaction.
     */
    @Override
    public String getType() {
        return "Expense";
    }

    /**
     * Returns the category of the expense.
     *
     * @return The category of the expense.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the account associated with the expense.
     *
     * @return The account associated with the expense.
     */
    @Override
    public String getAccount() {
        return account;
    }

    /**
     * Returns the note or description of the expense.
     *
     * @return The note or description of the expense.
     */
    @Override
    public String getNote() {
        return note;
    }

    /**
     * Returns the date of the expense.
     *
     * @return The date of the expense.
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * Returns the amount of the expense.
     *
     * @return The amount of the expense.
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the unique identifier of the expense.
     *
     * @return The unique identifier of the expense.
     */
    @Override
    public long getId() {
        return id;
    }
}
