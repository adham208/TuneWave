package com.TuneWave;

import com.TuneWave.Utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;

public class PlayerController {
    public Button browser;
    public Slider VolumeSlider;
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



    @FXML
    protected void browseSong(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose a song");
        fileChooser.setInitialDirectory(new File("F://Downloads"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files","*.mp3"));
        File choosenFile = fileChooser.showOpenDialog(browser.getScene().getWindow());
        if(choosenFile != null){
            String uri = FileUtils.fileToURI(choosenFile);
            player.dispose();
            player = new MediaPlayer(new Media(uri));
            onPlayButtonClick();
        }

    }

}