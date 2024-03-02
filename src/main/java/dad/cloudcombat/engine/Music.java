package dad.cloudcombat.engine;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Music {
	private MediaPlayer mediaPlayer;
	 private boolean playOnSelect;

    public Music(String musicFile) {
        Media sound = new Media(getClass().getResource(musicFile).toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    public boolean isPlaying() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
    
    public void changeSong(String musicFile) {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(musicFile).toString()));
        mediaPlayer.setOnEndOfMedia(() -> {
            if (playOnSelect) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
        if (playOnSelect) {
            mediaPlayer.play();
        }
    }

    public void setPlayOnSelect(boolean playOnSelect) {
        this.playOnSelect = playOnSelect;
    }
    
    public void playSound(String soundFile) {
        Media sound = new Media(getClass().getResource(soundFile).toString());
        MediaPlayer soundPlayer = new MediaPlayer(sound);
        soundPlayer.play();
    }
    
}
