package dad.cloudcombat.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class GameController {
	
	private EventHandler<ActionEvent> onBack;

	
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
    	onBack.handle(event);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public VBox getView() {
		return view;
	}
	
	public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

}
