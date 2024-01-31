package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SettingsController implements Initializable {

	// actions

	private EventHandler<ActionEvent> onSaveChanges;
	private EventHandler<ActionEvent> onBack;

	 @FXML
	    private Slider fxSlider;

	    @FXML
	    private JFXToggleButton fxToggle;

	    @FXML
	    private Slider generalSlider;

	    @FXML
	    private JFXToggleButton generalToggle;

	    @FXML
	    private ComboBox<?> songCombo;

	    @FXML
	    private Slider songSlider;

	    @FXML
	    private JFXToggleButton songToggle;

	    @FXML
	    private ComboBox<?> themeCombo;

	    @FXML
	    private VBox view;

	public SettingsController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsView.fxml"));
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
	void onSaveChangesAction(ActionEvent event) {
		onSaveChanges.handle(event);
	}

	@FXML
	void onBackToMenuAction(ActionEvent event) {
		onBack.handle(event);
	}

	public VBox getView() {
		return view;
	}

	public void setOnSaveChanges(EventHandler<ActionEvent> onSaveChanges) {
		this.onSaveChanges = onSaveChanges;
	}
	
	public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

}
