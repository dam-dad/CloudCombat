package dad.cloudcombat.engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class IA {
	 private static List<Button> selectedButtons = new ArrayList<>();

	    public static void selectAndClickRandomButton(GridPane playerGrid) {
	        int numRows = playerGrid.getRowConstraints().size();
	        int numCols = playerGrid.getColumnConstraints().size();

	        // Calcular el número total de botones en el playerGrid
	        int totalButtons = numRows * numCols;

	        // Filtrar los botones que aún no han sido seleccionados
	        List<Button> unselectedButtons = new ArrayList<>();
	        for (int i = 0; i < totalButtons; i++) {
	            Button button = (Button) playerGrid.getChildren().get(i);
	            if (!selectedButtons.contains(button)) {
	                unselectedButtons.add(button);
	            }
	        }

	        // Verificar si hay botones disponibles para seleccionar
	        if (!unselectedButtons.isEmpty()) {
	            // Generar un índice aleatorio dentro del rango de la cantidad total de botones
	            int randomIndex = (int) (Math.random() * unselectedButtons.size());

	            // Obtener el botón aleatorio de la lista de botones no seleccionados
	            Button randomButton = unselectedButtons.get(randomIndex);

	            // Crear un objeto PauseTransition para esperar un segundo antes de hacer clic en el botón
	            PauseTransition pause = new PauseTransition(Duration.seconds(1));
	            pause.setOnFinished(event -> {
	                // Simular el clic en el botón aleatorio
	                randomButton.fire();

	                // Agregar el botón seleccionado a la lista de botones seleccionados
	                selectedButtons.add(randomButton);
	            });
	            pause.play();
	        }
	    }
}
