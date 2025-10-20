package com.example.mediaplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        MediaPlayer player = new MediaPlayer(new Media("file:///C:/Users/Adham/Downloads/Morning.mp3"));
        player.play();
    }
}