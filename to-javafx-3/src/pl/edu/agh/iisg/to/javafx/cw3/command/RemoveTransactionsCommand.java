package pl.edu.agh.iisg.to.javafx.cw3.command;

import pl.edu.agh.iisg.to.javafx.cw3.model.*;

import java.util.*;

/**
 * Created by onegrx on 24.10.16.
 */
public class RemoveTransactionsCommand implements Command {

    private final List<Transaction> transactionsToRemove;
    private final Account account;
    private final StringBuilder removedTransactions = new StringBuilder();

    public RemoveTransactionsCommand(List<Transaction> transactionsToRemove, Account account) {
        transactionsToRemove.forEach(transaction -> {
            removedTransactions.append(transaction);
            removedTransactions.append("; ");
        });
        this.transactionsToRemove = transactionsToRemove;
        this.account = account;
    }

    @Override
    public void execute() {
        System.out.println("Number of transactions to remove: " + transactionsToRemove.size());
        for(Transaction t: transactionsToRemove) {
            System.out.println(t);
            account.removeTransaction(t);
        }


    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public String getName() {
        return "Removed transactions: " + removedTransactions.toString();
    }
}