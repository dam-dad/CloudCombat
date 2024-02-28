package dad.cloudcombat.engine;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Score {
    private String filePath;

    public Score(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            if (!Files.exists(Paths.get(filePath))) {
                // Si el archivo no existe, crea un JSONArray vacío y escribe en el archivo
                JSONArray emptyArray = new JSONArray();
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(emptyArray.toString(4)); // Formatear con sangrías de 4 espacios
                    fileWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertScore(String player, int score) {
        try {
            // Leer el contenido del archivo JSON
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Crear un JSONArray a partir del contenido existente
            JSONArray scoreArray = new JSONArray(content);

            // Crear un JSONObject para representar la nueva puntuación
            JSONObject newScore = new JSONObject();
            newScore.put("Player", player);
            newScore.put("Score", score);

            // Agregar la nueva puntuación al JSONArray
            scoreArray.put(newScore);

            // Escribir el JSONArray actualizado en el archivo JSON
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(scoreArray.toString(4)); // Formatear con sangrías de 4 espacios
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso:
        Score score = new Score("scores.json");
        score.insertScore("Jugador1", 100);
        score.insertScore("Jugador2", 150);
        score.insertScore("Jugador3", 200);
    }
}