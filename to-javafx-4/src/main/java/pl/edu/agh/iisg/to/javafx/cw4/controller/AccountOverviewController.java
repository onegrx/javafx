package pl.edu.agh.iisg.to.javafx.cw4.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.application.*;
import javafx.beans.binding.Bindings;
import javafx.concurrent.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iisg.to.javafx.cw4.model.Account;
import pl.edu.agh.iisg.to.javafx.cw4.model.Category;
import pl.edu.agh.iisg.to.javafx.cw4.model.Transaction;
import pl.edu.agh.iisg.to.javafx.cw4.model.generator.*;

public class AccountOverviewController {

    private static class TransactionGeneratorService extends Service<Void> {

        private final Account account;

        public TransactionGeneratorService(Account account) {
            this.account = account;
        }

        @Override
        protected Task<Void> createTask() {

            return new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    int iterations;

                    final int maxIterations = 20;
                    for (iterations = 0; iterations < maxIterations; iterations++) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                        updateMessage("Iteration " + iterations);
                        updateProgress(iterations + 1, maxIterations);

                        final Transaction t = doHardStuff();
                        if (t != null) {
                            // runs the method in UI thread
                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    account.addTransaction(t);

                                }

                            });
                        }
                    }
                    return null;
                }

                private Transaction doHardStuff() {
                    try {
                        Thread.sleep(100);
                        return DataGenerator.generateTransaction();
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                        }
                        return null;
                    }

                }
            };
        }

    }

	private Account data;

	private AccountAppController appController;

	@FXML
	private TableView<Transaction> transactionsTable;

	@FXML
	private TableColumn<Transaction, LocalDate> dateColumn;

	@FXML
	private TableColumn<Transaction, String> payeeColumn;

	@FXML
	private TableColumn<Transaction, Category> categoryColumn;

	@FXML
	private TableColumn<Transaction, BigDecimal> inflowColumn;

	@FXML
	private Button deleteButton;

	@FXML
	private Button editButton;

	@FXML
	private Button addButton;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private void initialize() {

		transactionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		dateColumn.setCellValueFactory(dataValue -> dataValue.getValue().getDateProperty());
		payeeColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPayeeProperty());
		categoryColumn.setCellValueFactory(dataValue -> dataValue.getValue().getCategoryProperty());
		inflowColumn.setCellValueFactory(dataValue -> dataValue.getValue().getInflowProperty());

		deleteButton.disableProperty().bind(Bindings.isEmpty(transactionsTable.getSelectionModel().getSelectedItems()));

		editButton.disableProperty()
				.bind(Bindings.size(transactionsTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));

		progressBar.setVisible(false);
	}

	@FXML
	private void handleDeleteAction(ActionEvent event) {
		transactionsTable.getItems().removeAll(transactionsTable.getSelectionModel().getSelectedItems());
	}

	@FXML
	private void handleEditAction(ActionEvent event) {
		Transaction transaction = transactionsTable.getSelectionModel().getSelectedItem();
		if (transaction != null) {
			appController.showTransactionEditDialog(transaction);
		}
	}

	@FXML
	private void handleAddAction(ActionEvent event) {
		Transaction transaction = Transaction.newTransaction();

		if (appController.showTransactionEditDialog(transaction)) {
			data.addTransaction(transaction);
		}
	}

	@FXML
	private void handleLoadData(ActionEvent event) {
		TransactionGeneratorService service = new TransactionGeneratorService(data);
		progressBar.progressProperty().bind(service.progressProperty());
		progressBar.visibleProperty().bind(service.runningProperty());
		service.start();
	}

	public void setData(Account acccount) {
		this.data = acccount;
		transactionsTable.setItems(data.getTransactions());
	}

	public void setAppController(AccountAppController appController) {
		this.appController = appController;
	}
}
