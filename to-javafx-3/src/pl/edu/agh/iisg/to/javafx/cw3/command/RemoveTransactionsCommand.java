package pl.edu.agh.iisg.to.javafx.cw3.command;

import pl.edu.agh.iisg.to.javafx.cw3.model.*;

import java.util.*;

/**
 * Created by onegrx on 24.10.16.
 */
public class RemoveTransactionsCommand implements Command {

    private final List<Transaction> transactionsToRemove = new ArrayList<>();
    private final Account account;
    private final StringBuilder removedTransactions = new StringBuilder();

    public RemoveTransactionsCommand(List<Transaction> transactionsToRemove, Account account) {
        transactionsToRemove.forEach(transaction -> {
            removedTransactions.append(transaction);
            removedTransactions.append("; ");
        });
        this.transactionsToRemove.addAll(transactionsToRemove);
        this.account = account;
    }

    @Override
    public void execute() {
        transactionsToRemove.forEach(account::removeTransaction);
    }

    @Override
    public void undo() {
        transactionsToRemove.forEach(account::addTransaction);
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {
        return "Removed transactions: " + removedTransactions.toString();
    }
}
