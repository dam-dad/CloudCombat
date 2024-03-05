package dad.cloudcombat.engine;
/**
 * @author David 
 */
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class IA {
	private static List<Button> selectedButtons = new ArrayList<>();

	private static Music shot = new Music("/assets/FX/shot.mp3");

	/**
	 * Esta función es la que hace que la IA pulse botones aleatorios en el tablero
	 * que recibe por parámetro
	 *
	 */
	public static void selectAndClickRandomButton(GridPane playerGrid) {
		int numRows = playerGrid.getRowConstraints().size();
		int numCols = playerGrid.getColumnConstraints().size();

		int totalButtons = numRows * numCols;

		List<Button> unselectedButtons = new ArrayList<>();
		for (int i = 0; i < totalButtons; i++) {
			Button button = (Button) playerGrid.getChildren().get(i);
			if (!selectedButtons.contains(button)) {
				unselectedButtons.add(button);
			}
		}

		if (!unselectedButtons.isEmpty()) {
			int randomIndex = (int) (Math.random() * unselectedButtons.size());

			Button randomButton = unselectedButtons.get(randomIndex);

			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(event -> {
				randomButton.fire();
				selectedButtons.add(randomButton);
			});
			pause.play();
		}
	}
}
