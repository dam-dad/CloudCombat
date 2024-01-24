package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {

	@FXML
	private VBox view;

	public MenuController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onStartGameAction(ActionEvent event) {
	
	}
	

	@FXML
	void onExitAction(ActionEvent event) {

	}

	@FXML
	void onSettingsAction(ActionEvent event) {
		
	
	}

	public VBox getView() {
		return view;
	}

}
