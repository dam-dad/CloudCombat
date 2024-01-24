package dad.cloudcombat.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class GameController {
	
	@FXML
    private VBox view;

    
    public GameController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    @FXML
    void onGoingBackToMenuAction(ActionEvent event) {
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public VBox getView() {
		return view;
	}

}
