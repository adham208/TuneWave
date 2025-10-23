package com.TuneWave;

import com.TuneWave.Utils.FileUtils;
import com.TuneWave.Utils.TimeUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;

public class PlayerController {
    public Label length;
    public Button playButton;
    public Label currlength;
    @FXML
    private Button browser;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label songLabel;
    protected MediaPlayer player = new MediaPlayer(new Media("file:///C:/Users/Adham/Downloads/Morning.mp3"));
    protected boolean isPlaying;

    @FXML
    public void initialize(){
        songLabel.setText("No Song Selected");
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);

        volumeSlider.setValue(50);

        volumeSlider.valueProperty().addListener(
                (obs,oldVal,newVal)->
                        player.setVolume(newVal.doubleValue()/100.0));


    }

    @FXML
    protected void onPlayButtonClick() {
        if(isPlaying){
            player.pause();
            playButton.setText("▶");
            isPlaying = false;
            return;
        }
        player.play();
        playButton.setText("⏸");
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
            songLabel.setText(choosenFile.getName());

            try{
                player = new MediaPlayer(new Media(uri));
                player.setVolume(volumeSlider.getValue()/100.0);
                player.setOnReady(()->
                        length.setText(TimeUtils.dateToStringReady(player.getTotalDuration())));
                player.currentTimeProperty().addListener((obs,oldVal,newVal)->currlength.setText(TimeUtils.dateToStringReady(newVal)));
                isPlaying = false;
                onPlayButtonClick();


            } catch (Exception e){
                songLabel.setText("Error Loading File");
            }


        }

    }

}