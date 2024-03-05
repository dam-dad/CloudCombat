package dad.cloudcombat.engine;
/**
 * @author David 
 */
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
/**
 * función que crea el fichero json
 */
    private void createFileIfNotExists() {
        try {
            if (!Files.exists(Paths.get(filePath))) {
                JSONArray emptyArray = new JSONArray();
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(emptyArray.toString(4));
                    fileWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Función que inserta en el json la nueva puntuación del jugador
 * @param player nombre del jugador
 * @param score puntuacion del jugador
 */
    public void insertScore(String player, int score) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONArray scoreArray = new JSONArray(content);

            JSONObject newScore = new JSONObject();
            newScore.put("Player", player);
            newScore.put("Score", score);

            scoreArray.put(newScore);

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
                fileWriter.write(sortedScoreArray.toString(4));
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * función que lee las puntuaciones del fichero y las convierte en una lista
     * @return lista de puntuaciones
     */
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