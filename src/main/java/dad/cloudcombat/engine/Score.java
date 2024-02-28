package dad.cloudcombat.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Score {
	 private String filePath;
	 private static final String FILE_NAME = "scores.json"; // Nombre del archivo


	    public Score(String filePath) {
	    	this.filePath = getClass().getResource("/scores.json").getPath();
	    }

	    public static class ScoreEntry {
	        private String player;
	        private int score;

	        public ScoreEntry(String player, int score) {
	            this.player = player;
	            this.score = score;
	        }

	        public String getPlayer() {
	            return player;
	        }

	        public int getScore() {
	            return score;
	        }
	    }

	    public void insertScore(String player, int score) {
	        try {
	            // Leer el contenido del archivo JSON (si existe)
	            String content = "";
	            if (Files.exists(Paths.get(filePath))) {
	                content = new String(Files.readAllBytes(Paths.get(filePath)));
	            }

	            // Crear un JSONArray a partir del contenido existente o uno nuevo si está vacío
	            JSONArray scoreArray = new JSONArray(content);

	            // Crear un JSONObject para representar la nueva puntuación
	            JSONObject newScore = new JSONObject();
	            newScore.put("Player", player);
	            newScore.put("Score", score);

	            // Agregar la nueva puntuación al JSONArray
	            scoreArray.put(newScore);

	            // Escribir el JSONArray actualizado en el archivo JSON
	            try (FileWriter file = new FileWriter(filePath)) {
	                file.write(scoreArray.toString());
	                file.flush();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<ScoreEntry> readScores() {
	        List<ScoreEntry> scores = new ArrayList<>();
	        try {
	            // Leer el contenido del archivo JSON
	            String content = new String(Files.readAllBytes(Paths.get(filePath)));
	            JSONArray scoreArray = new JSONArray(content);

	            // Iterar sobre el JSONArray y crear objetos ScoreEntry
	            for (int i = 0; i < scoreArray.length(); i++) {
	                JSONObject scoreObject = scoreArray.getJSONObject(i);
	                String player = scoreObject.getString("Player");
	                int score = scoreObject.getInt("Score");
	                scores.add(new ScoreEntry(player, score));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return scores;
	    }
	    
	    public void insertTestData() {
	        // Método para insertar datos de prueba en el archivo JSON
	        
	        // Agregar datos de prueba
	        insertScore("Jugador1", 100);
	        insertScore("Jugador2", 150);
	        insertScore("Jugador3", 200);
	    }
	
}
