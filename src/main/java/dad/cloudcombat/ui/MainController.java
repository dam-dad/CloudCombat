package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.cloudcombat.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
	//controllers
	
	private MenuController menuController;
	private SettingsController settingsController;
	private DifficultyController difficultyController;
	private GameController gameController;
	
	@FXML
    private BorderPane view;
	
	 public MainController() {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
				loader.setController(this);
				loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuController = new  MenuController();
		settingsController = new SettingsController();
		difficultyController = new DifficultyController();
		gameController = new GameController();
		
		view.setCenter(menuController.getView());
		
		
		menuController.setOnStartGame(e -> {
			view.setCenter(difficultyController.getView());
		});
		
		menuController.setOnSettings(e -> {
			view.setCenter(settingsController.getView());
		});
		
		menuController.setOnExit(e -> {
			 App.stage.close();
			  
		});
		
		settingsController.setOnSaveChanges(e -> {
			view.setCenter(menuController.getView());
		});
		
		settingsController.setOnBack(e -> {
			view.setCenter(menuController.getView());
		});
		
		difficultyController.setOnEasy(e -> {
			view.setCenter(gameController.getView());
		});
		
		gameController.setOnBack(e -> {
			view.setCenter(menuController.getView());
		});
		
	}
	
	
	
	public BorderPane getView() {
		return view;
	}

}
