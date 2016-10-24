package pl.edu.agh.iisg.to.javafx.cw4.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to.javafx.cw4.Main;
import pl.edu.agh.iisg.to.javafx.cw4.model.Transaction;
import pl.edu.agh.iisg.to.javafx.cw4.model.generator.DataGenerator;

public class AccountAppController {

	private Stage primaryStage;

	public AccountAppController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void initRootLayout() {
		try {
			this.primaryStage.setTitle("My second JavaFX app");

			// load layout from FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AccountOverviewPane.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();

			// set initial data into controller
			AccountOverviewController controller = loader.getController();
			controller.setAppController(this);
			controller.setData(DataGenerator.generateAccountData());

			// add layout to a scene and show them all
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// don't do this in common apps
			e.printStackTrace();
		}

	}

	public boolean showTransactionEditDialog(Transaction transaction) {
		try {
			// Load the fxml file and create a new stage for the dialog
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/TransactionEditDialog.fxml"));
			BorderPane page = (BorderPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit transaction");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			TransactionEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setData(transaction);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.isApproved();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
