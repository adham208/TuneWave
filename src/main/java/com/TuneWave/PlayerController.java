package com.TuneWave;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerController {
    protected MediaPlayer player = new MediaPlayer(new Media("file:///C:/Users/Adham/Downloads/Morning.mp3"));
    protected boolean isPlaying;
    @FXML
    protected void onPlayButtonClick() {
        if(isPlaying){
            player.pause();
            isPlaying = false;
            return;
        }
        player.play();
        isPlaying = true;
    }

}