package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameController implements Initializable {

	private EventHandler<ActionEvent> onBack;

	@FXML
	private StackPane view;

	@FXML
	private TableColumn<?, ?> playerColumn;

	@FXML
	private TableColumn<?, ?> rankColumn;

	@FXML
	private TableColumn<?, ?> scoreColumn;

	@FXML
	private BorderPane view2;

	@FXML
	private TableView<?> scoreTable;

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

	public StackPane getView() {
		return view;
	}

	public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onAceptar(ActionEvent event) {
		view2.setVisible(false);
	}

	@FXML
	void onShowScores(ActionEvent event) {
		view2.setVisible(true);
	}

}
