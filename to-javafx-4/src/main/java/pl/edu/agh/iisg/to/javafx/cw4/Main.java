package pl.edu.agh.iisg.to.javafx.cw4;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to.javafx.cw4.controller.AccountAppController;

public class Main extends Application {

	private Stage primaryStage;
	
	private AccountAppController presenter;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My first JavaFX app");

		this.presenter = new AccountAppController(primaryStage);
		this.presenter.initRootLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
