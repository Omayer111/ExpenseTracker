package com.example.expensetracker.Model;

/**
 * The TransactionFactory class creates instances of specific Transaction implementations based on the transaction type.
 */
public class TransactionFactory {

    /**
     * Creates and returns a new instance of a Transaction based on the provided parameters.
     *
     * @param transactionType The type of the transaction ("INCOME" or "EXPENSE").
     * @param category The category of the transaction.
     * @param account The account associated with the transaction.
     * @param note A note or description for the transaction.
     * @param date The date of the transaction.
     * @param amount The amount of the transaction.
     * @param id The unique identifier for the transaction.
     * @return A new instance of a Transaction based on the provided parameters.
     */
    public Transaction getTransaction(String transactionType, String category, String account, String note, String date, double amount, long id) {
        if (transactionType == null) {
            return null;
        }
        if (transactionType.equalsIgnoreCase("INCOME")) {
            return new Income(category, account, note, date, amount, id);
        } else if (transactionType.equalsIgnoreCase("EXPENSE")) {
            return new Expense(category, account, note, date, amount, id);
        }
        return null;
    }
}
