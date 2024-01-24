package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class DifficultyController implements Initializable {
	
	private GameController gameController = new GameController();
	
	@FXML
    private VBox view;

    
    public DifficultyController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DifficultyView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@FXML
	void onSelectEasyDifficultyAction(ActionEvent event) {
		VBox gameView = gameController.getView();
	    view.getChildren().clear();
	    view.getChildren().add(gameView);
	}
	
	@FXML
	void onSelectStandardDifficultyAction(ActionEvent event) {
		
	}
	
	public VBox getView() {
		return view;
	}

}
