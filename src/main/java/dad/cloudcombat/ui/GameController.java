package dad.cloudcombat.ui;
/**
 * @author David
 */
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import dad.cloudcombat.engine.IA;
import dad.cloudcombat.engine.Music;
import dad.cloudcombat.engine.Score;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {

	private EventHandler<ActionEvent> onBack;

	private Score scoreManager;

	private static Music explosion = new Music("/assets/FX/explosion.aiff");

	private List<Image> imageListPlayer;
	private List<Image> imageListIA;

	private int score = 0;
	private int buttonsClickedByPlayer = 12;
	private int buttonsClickedbyIA = 12;

	@FXML
	private Label scoreLabel;

	@FXML
	private StackPane view;

	@FXML
	private TableColumn<Score.ScoreData, String> playerColumn;

	@FXML
	private TableColumn<Score.ScoreData, Integer> rankColumn;

	@FXML
	private TableColumn<Score.ScoreData, Integer> scoreColumn;

	@FXML
	private BorderPane view2;

	@FXML
	private TableView<Score.ScoreData> scoreTable;

	/**
	 * Constructor
	 */
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

	/**
	 * Función que asigna imágenes aleatorias en casillas aleatorias en el tablero
	 * del jugador. Tiene el evento on click para cambiar de color las casillas que
	 * son presionadas y el fx de la explosión
	 */
	private void asignarImagenAleatoriaPlayer() {
		List<Integer> indices = new ArrayList<>();
		for (int i = 0; i < 100; i++) {

			indices.add(i);
			int indiceBoton = indices.get(i);
			String buttonId = "buttonPlayer" + String.format("%02d", indiceBoton);
			Button button = (Button) view.lookup("#" + buttonId);
			button.setOpacity(0.2);
			button.setOnAction(event -> {
				button.setStyle("-fx-background-color: blue;");

			});

		}
		Collections.shuffle(indices);

		for (int i = 0; i < 12; i++) {
			int indiceBoton = indices.get(i);

			Random random = new Random();
			Image imagenAleatoria = imageListPlayer.get(random.nextInt(imageListPlayer.size()));

			ImageView imageView = new ImageView(imagenAleatoria);
			imageView.setFitWidth(32);
			imageView.setFitHeight(32);

			String buttonId = "buttonPlayer" + String.format("%02d", indiceBoton);
			Button button = (Button) view.lookup("#" + buttonId);

			button.setMinSize(25, 25);
			button.setMaxSize(25, 25);

			button.setGraphic(imageView);
			button.setOpacity(1.0);

			button.setOnAction(event -> {
				button.setDisable(true);
				button.setStyle("-fx-background-color: #f15361;");
				explosion.playSound("/assets/FX/explosion.aiff");
				score = score - 10;
				updateScoreLabel();

				buttonsClickedbyIA--;

				if (buttonsClickedbyIA == 0) {
					scoreLabel.setText("¡Has perdido! Score: " + score);

				}

			});
		}
	}

	/**
	 * Función que asigna imágenes aleatorias en casillas aleatorias en el tablero
	 * de la IA. Tiene el evento on click para cambiar de color las casillas que son
	 * presionadas y el fx de la explosión
	 */
	private void asignarImagenAleatoriaIA() {
		List<Integer> indices = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			indices.add(i);
			int indiceBoton = indices.get(i);
			String buttonId = "buttonIA" + String.format("%02d", indiceBoton);
			Button button = (Button) view.lookup("#" + buttonId);
			button.setOpacity(0.2);
			button.setOnAction(event -> {
				button.setStyle("-fx-background-color: blue;");

			});

		}
		Collections.shuffle(indices);

		for (int i = 12; i < 24; i++) {
			int indiceBoton = indices.get(i);

			Random random = new Random();
			Image imagenAleatoria = imageListIA.get(random.nextInt(imageListIA.size()));

			ImageView imageView = new ImageView(imagenAleatoria);
			imageView.setFitWidth(32);
			imageView.setFitHeight(32);

			String buttonId = "buttonIA" + String.format("%02d", indiceBoton);
			Button button = (Button) view.lookup("#" + buttonId);

			button.setMinSize(25, 25);
			button.setMaxSize(25, 25);

			button.setGraphic(imageView);
			button.setOpacity(0.0);
			button.setOnAction(event -> {
				button.setStyle("-fx-background-color: lightgreen;");
				button.setOpacity(1.0);

			});

		}
	}

	/**
	 * Carga las imagenes de los dos tablerosss
	 */
	private void cargarImagenes() {

		imageListPlayer = new ArrayList<>();
		for (int i = 0; i <= 11; i++) {
			String imagePath = String.format("/assets/planes/plane%02d.png", i);
			Image image = new Image(getClass().getResourceAsStream(imagePath));
			imageListPlayer.add(image);
		}
		imageListIA = new ArrayList<>();
		for (int i = 12; i <= 23; i++) {
			String imagePath = String.format("/assets/planes/plane%02d.png", i);
			Image image = new Image(getClass().getResourceAsStream(imagePath));
			imageListIA.add(image);
		}

	}

	@FXML
	void onIAButtonClicked(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();

		if (!clickedButton.isDisabled() && clickedButton.getGraphic() != null) {
			clickedButton.setStyle("-fx-background-color: lightgreen;");
			explosion.playSound("/assets/FX/explosion.aiff");
			clickedButton.setOpacity(1.0);

			if (playerGrid.getChildren().contains(clickedButton)) {
				score--;
				updateScoreLabel();
			} else {
				score = score + 10;
				updateScoreLabel();
			}

			clickedButton.setDisable(true);

			if (clickedButton.getGraphic() != null) {
				buttonsClickedByPlayer--;
				if (buttonsClickedByPlayer == 0) {
					scoreLabel.setText("¡HAS GANADO! Score: " + score);
					view2.setVisible(true);

					// Guardar el puntaje en el archivo JSON
					Score scoreManager = new Score("scores.json");
					scoreManager.insertScore("PLAYER1", score);

					updateScoreTable(scoreManager);
				}
			}
		} else {
			clickedButton.setStyle("-fx-background-color: blue;");
		}

		IA.selectAndClickRandomButton(playerGrid);
	}

	/**
	 * carga sobre la tabla el score obtenido
	 * 
	 * @param scoreManager recibe la puntuacion del scoreLabel
	 */
	private void updateScoreTable(Score scoreManager) {
		List<Score.ScoreData> scores = scoreManager.readScores();
		ObservableList<Score.ScoreData> scoreDataList = FXCollections.observableArrayList(scores);
		scoreTable.setItems(scoreDataList);
	}

	/**
	 * Actualiza el score
	 */
	private void updateScoreLabel() {
		scoreLabel.setText("SCORE: " + score);
	}

	/**
	 * Cuando aceptas la ventana de puntuaciones desactivas la visibilidad para
	 * volver al juego
	 * 
	 */
	@FXML
	void onAceptar(ActionEvent event) {
		view2.setVisible(false);
	}

	/**
	 * 
	 * Muestra la pantalla de las puntuaciones
	 */
	@FXML
	void onShowScores(ActionEvent event) {
		view2.setVisible(true);
	}

	/**
	 * 
	 * boton en el que se incluye el initialize, carga las imagenes, los valores en
	 * la tabla, etc
	 */
	@FXML
	void onStartGame(ActionEvent event) {

		cargarImagenes();
		asignarImagenAleatoriaPlayer();
		asignarImagenAleatoriaIA();

		for (Node node : iaGrid.getChildren()) {
			if (node instanceof Button) {
				Button button = (Button) node;
				button.setOnAction(this::onIAButtonClicked);
			}
		}

		updateScoreLabel();

		scoreManager = new Score("scores.json");

		// Configurar las celdas de la tabla
		playerColumn.setCellValueFactory(new PropertyValueFactory<>("player"));
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

		List<Score.ScoreData> scores = scoreManager.readScores();
		List<Score.ScoreData> topFiveScores = scores.subList(0, Math.min(scores.size(), 5));
		ObservableList<Score.ScoreData> scoreData = FXCollections.observableArrayList(topFiveScores);
		scoreTable.setItems(scoreData);

		rankColumn.setCellValueFactory(cellData -> {
			int index = cellData.getTableView().getItems().indexOf(cellData.getValue());
			if (index >= 0 && index < 5) {
				return new SimpleIntegerProperty(index + 1).asObject();
			} else {
				return null;
			}
		});
	}

	/**
	 * Resetea el GameView al principio para volver a jugar
	 */
	@FXML
	void onResetGame(ActionEvent event) {
		score = 0;
		scoreLabel.setText("SCORE: " + score);

		buttonsClickedByPlayer = 12;
		buttonsClickedbyIA = 12;

		asignarImagenAleatoriaPlayer();
		asignarImagenAleatoriaIA();

		// Limpiar todas las imágenes de los botones y habilitarlos en iaGrid
		for (Node node : iaGrid.getChildren()) {
			if (node instanceof Button) {
				Button button = (Button) node;
				button.setDisable(false);
				button.setOpacity(0.2);
				button.setStyle("-fx-background-color: transparent;");
				button.setGraphic(null); // Eliminar la imagen del botón
			}
		}

		// Limpiar todas las imágenes de los botones en playerGrid
		for (Node node : playerGrid.getChildren()) {
			if (node instanceof Button) {
				Button button = (Button) node;
				button.setGraphic(null);
				button.setStyle("-fx-background-color: transparent;");
			}
		}

		IA.selectAndClickRandomButton(playerGrid);
	}

	@FXML
	private Button backButton;

	@FXML
	private Button buttonIA00;

	@FXML
	private Button buttonIA01;

	@FXML
	private Button buttonIA02;

	@FXML
	private Button buttonIA03;

	@FXML
	private Button buttonIA04;

	@FXML
	private Button buttonIA05;

	@FXML
	private Button buttonIA06;

	@FXML
	private Button buttonIA07;

	@FXML
	private Button buttonIA08;

	@FXML
	private Button buttonIA09;

	@FXML
	private Button buttonIA10;

	@FXML
	private Button buttonIA11;

	@FXML
	private Button buttonIA12;

	@FXML
	private Button buttonIA13;

	@FXML
	private Button buttonIA14;

	@FXML
	private Button buttonIA15;

	@FXML
	private Button buttonIA16;

	@FXML
	private Button buttonIA17;

	@FXML
	private Button buttonIA18;

	@FXML
	private Button buttonIA19;

	@FXML
	private Button buttonIA20;

	@FXML
	private Button buttonIA21;

	@FXML
	private Button buttonIA22;

	@FXML
	private Button buttonIA23;

	@FXML
	private Button buttonIA24;

	@FXML
	private Button buttonIA25;

	@FXML
	private Button buttonIA26;

	@FXML
	private Button buttonIA27;

	@FXML
	private Button buttonIA28;

	@FXML
	private Button buttonIA29;

	@FXML
	private Button buttonIA30;

	@FXML
	private Button buttonIA31;

	@FXML
	private Button buttonIA32;

	@FXML
	private Button buttonIA33;

	@FXML
	private Button buttonIA34;

	@FXML
	private Button buttonIA35;

	@FXML
	private Button buttonIA36;

	@FXML
	private Button buttonIA37;

	@FXML
	private Button buttonIA38;

	@FXML
	private Button buttonIA39;

	@FXML
	private Button buttonIA40;

	@FXML
	private Button buttonIA41;

	@FXML
	private Button buttonIA42;

	@FXML
	private Button buttonIA43;

	@FXML
	private Button buttonIA44;

	@FXML
	private Button buttonIA45;

	@FXML
	private Button buttonIA46;

	@FXML
	private Button buttonIA47;

	@FXML
	private Button buttonIA48;

	@FXML
	private Button buttonIA49;

	@FXML
	private Button buttonIA50;

	@FXML
	private Button buttonIA51;

	@FXML
	private Button buttonIA52;

	@FXML
	private Button buttonIA53;

	@FXML
	private Button buttonIA54;

	@FXML
	private Button buttonIA55;

	@FXML
	private Button buttonIA56;

	@FXML
	private Button buttonIA57;

	@FXML
	private Button buttonIA58;

	@FXML
	private Button buttonIA59;

	@FXML
	private Button buttonIA60;

	@FXML
	private Button buttonIA61;

	@FXML
	private Button buttonIA62;

	@FXML
	private Button buttonIA63;

	@FXML
	private Button buttonIA64;

	@FXML
	private Button buttonIA65;

	@FXML
	private Button buttonIA66;

	@FXML
	private Button buttonIA67;

	@FXML
	private Button buttonIA68;

	@FXML
	private Button buttonIA69;

	@FXML
	private Button buttonIA70;

	@FXML
	private Button buttonIA71;

	@FXML
	private Button buttonIA72;

	@FXML
	private Button buttonIA73;

	@FXML
	private Button buttonIA74;

	@FXML
	private Button buttonIA75;

	@FXML
	private Button buttonIA76;

	@FXML
	private Button buttonIA77;

	@FXML
	private Button buttonIA78;

	@FXML
	private Button buttonIA79;

	@FXML
	private Button buttonIA80;

	@FXML
	private Button buttonIA81;

	@FXML
	private Button buttonIA82;

	@FXML
	private Button buttonIA83;

	@FXML
	private Button buttonIA84;

	@FXML
	private Button buttonIA85;

	@FXML
	private Button buttonIA86;

	@FXML
	private Button buttonIA87;

	@FXML
	private Button buttonIA88;

	@FXML
	private Button buttonIA89;

	@FXML
	private Button buttonIA90;

	@FXML
	private Button buttonIA91;

	@FXML
	private Button buttonIA92;

	@FXML
	private Button buttonIA93;

	@FXML
	private Button buttonIA94;

	@FXML
	private Button buttonIA95;

	@FXML
	private Button buttonIA96;

	@FXML
	private Button buttonIA97;

	@FXML
	private Button buttonIA98;

	@FXML
	private Button buttonIA99;

	@FXML
	private Button buttonPlayer00;

	@FXML
	private Button buttonPlayer01;

	@FXML
	private Button buttonPlayer02;

	@FXML
	private Button buttonPlayer03;

	@FXML
	private Button buttonPlayer04;

	@FXML
	private Button buttonPlayer05;

	@FXML
	private Button buttonPlayer06;

	@FXML
	private Button buttonPlayer07;

	@FXML
	private Button buttonPlayer08;

	@FXML
	private Button buttonPlayer09;

	@FXML
	private Button buttonPlayer10;

	@FXML
	private Button buttonPlayer11;

	@FXML
	private Button buttonPlayer12;

	@FXML
	private Button buttonPlayer13;

	@FXML
	private Button buttonPlayer14;

	@FXML
	private Button buttonPlayer15;

	@FXML
	private Button buttonPlayer16;

	@FXML
	private Button buttonPlayer17;

	@FXML
	private Button buttonPlayer18;

	@FXML
	private Button buttonPlayer19;

	@FXML
	private Button buttonPlayer20;

	@FXML
	private Button buttonPlayer21;

	@FXML
	private Button buttonPlayer22;

	@FXML
	private Button buttonPlayer23;

	@FXML
	private Button buttonPlayer24;

	@FXML
	private Button buttonPlayer25;

	@FXML
	private Button buttonPlayer26;

	@FXML
	private Button buttonPlayer27;

	@FXML
	private Button buttonPlayer28;

	@FXML
	private Button buttonPlayer29;

	@FXML
	private Button buttonPlayer30;

	@FXML
	private Button buttonPlayer31;

	@FXML
	private Button buttonPlayer32;

	@FXML
	private Button buttonPlayer33;

	@FXML
	private Button buttonPlayer34;

	@FXML
	private Button buttonPlayer35;

	@FXML
	private Button buttonPlayer36;

	@FXML
	private Button buttonPlayer37;

	@FXML
	private Button buttonPlayer38;

	@FXML
	private Button buttonPlayer39;

	@FXML
	private Button buttonPlayer40;

	@FXML
	private Button buttonPlayer41;

	@FXML
	private Button buttonPlayer42;

	@FXML
	private Button buttonPlayer43;

	@FXML
	private Button buttonPlayer44;

	@FXML
	private Button buttonPlayer45;

	@FXML
	private Button buttonPlayer46;

	@FXML
	private Button buttonPlayer47;

	@FXML
	private Button buttonPlayer48;

	@FXML
	private Button buttonPlayer49;

	@FXML
	private Button buttonPlayer50;

	@FXML
	private Button buttonPlayer51;

	@FXML
	private Button buttonPlayer52;

	@FXML
	private Button buttonPlayer53;

	@FXML
	private Button buttonPlayer54;

	@FXML
	private Button buttonPlayer55;

	@FXML
	private Button buttonPlayer56;

	@FXML
	private Button buttonPlayer57;

	@FXML
	private Button buttonPlayer58;

	@FXML
	private Button buttonPlayer59;

	@FXML
	private Button buttonPlayer60;

	@FXML
	private Button buttonPlayer61;

	@FXML
	private Button buttonPlayer62;

	@FXML
	private Button buttonPlayer63;

	@FXML
	private Button buttonPlayer64;

	@FXML
	private Button buttonPlayer65;

	@FXML
	private Button buttonPlayer66;

	@FXML
	private Button buttonPlayer67;

	@FXML
	private Button buttonPlayer68;

	@FXML
	private Button buttonPlayer69;

	@FXML
	private Button buttonPlayer70;

	@FXML
	private Button buttonPlayer71;

	@FXML
	private Button buttonPlayer72;

	@FXML
	private Button buttonPlayer73;

	@FXML
	private Button buttonPlayer74;

	@FXML
	private Button buttonPlayer75;

	@FXML
	private Button buttonPlayer76;

	@FXML
	private Button buttonPlayer77;

	@FXML
	private Button buttonPlayer78;

	@FXML
	private Button buttonPlayer79;

	@FXML
	private Button buttonPlayer80;

	@FXML
	private Button buttonPlayer81;

	@FXML
	private Button buttonPlayer82;

	@FXML
	private Button buttonPlayer83;

	@FXML
	private Button buttonPlayer84;

	@FXML
	private Button buttonPlayer85;

	@FXML
	private Button buttonPlayer86;

	@FXML
	private Button buttonPlayer87;

	@FXML
	private Button buttonPlayer88;

	@FXML
	private Button buttonPlayer89;

	@FXML
	private Button buttonPlayer90;

	@FXML
	private Button buttonPlayer91;

	@FXML
	private Button buttonPlayer92;

	@FXML
	private Button buttonPlayer93;

	@FXML
	private Button buttonPlayer94;

	@FXML
	private Button buttonPlayer95;

	@FXML
	private Button buttonPlayer96;

	@FXML
	private Button buttonPlayer97;

	@FXML
	private Button buttonPlayer98;

	@FXML
	private Button buttonPlayer99;

	@FXML
	private GridPane iaGrid;

	@FXML
	private GridPane playerGrid;

	@FXML
	private VBox view2m;

}
