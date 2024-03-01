package dad.cloudcombat;

import dad.cloudcombat.ui.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

	MainController mainController;

	public static Stage stage;

	/**
	 * @
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		mainController = new MainController();

		primaryStage.setTitle("CloudCombat");

		Scene scene = new Scene(mainController.getView());
		scene.getStylesheets().add(getClass().getResource("/css/CloudCombat.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/planes/icon.jpg")));

		primaryStage.show();
	}

}
