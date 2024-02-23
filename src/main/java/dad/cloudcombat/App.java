package dad.cloudcombat;

import dad.cloudcombat.ui.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
	
	MainController mainController;
	
	public static Stage stage;

	/**
	 * @
	 * */
	@Override
	public void start(Stage primaryStage) throws Exception {
	    stage = primaryStage;
	    mainController = new MainController();
	    
	    //Font.loadFont(getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf"), 12);

	    primaryStage.setTitle("CloudCombat");

	    
	    Scene scene = new Scene(mainController.getView());
	    scene.getStylesheets().add(getClass().getResource("/css/Game.css").toExternalForm());

	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

}
