package dad.cloudcombat.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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

            // Convertir el JSONArray a una lista de objetos JSON
            List<JSONObject> jsonList = new ArrayList<>();
            for (int i = 0; i < scoreArray.length(); i++) {
                jsonList.add(scoreArray.getJSONObject(i));
            }

            // Ordenar la lista de objetos JSON por puntuación en orden descendente
            Collections.sort(jsonList, Comparator.comparingInt(o -> -o.getInt("Score")));

            // Crear un nuevo JSONArray a partir de la lista ordenada
            JSONArray sortedScoreArray = new JSONArray();
            for (JSONObject obj : jsonList) {
                sortedScoreArray.put(obj);
            }

            // Escribir el JSONArray actualizado en el archivo JSON
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(sortedScoreArray.toString(4)); // Formatear con sangrías de 4 espacios
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<ScoreData> readScores() {
        List<ScoreData> scores = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray scoreArray = new JSONArray(content);
            for (int i = 0; i < scoreArray.length(); i++) {
                JSONObject scoreObj = scoreArray.getJSONObject(i);
                String player = scoreObj.getString("Player");
                int score = scoreObj.getInt("Score");
                scores.add(new ScoreData(player, score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static class ScoreData {
        private String player;
        private int score;

        public ScoreData(String player, int score) {
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
}