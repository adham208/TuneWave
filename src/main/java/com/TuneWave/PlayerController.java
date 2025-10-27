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
import javafx.util.Duration;

import java.io.File;

public class PlayerController {
    public Label length;
    public Button playButton;
    public Label currLength;
    public boolean isSeeking;
    public Slider seekSlider;
    @FXML
    private Button browser;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label songLabel;
    protected MediaPlayer player;
    protected boolean isPlaying;

    @FXML
    public void initialize(){
        songLabel.setText("No Song Selected");
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setMaxWidth(100);
        volumeSlider.setValue(50);

        volumeSlider.valueProperty().addListener(
                (obs,oldVal,newVal)->
                        player.setVolume(newVal.doubleValue()/100.0));
        seekSlider.setOnMousePressed(event -> {
            isSeeking = true;
        });

        seekSlider.setOnMouseReleased(event->{
            player.seek(Duration.seconds(seekSlider.getValue()));
            isSeeking = false;
        });


    }

    @FXML
    protected void onPlayButtonClick() {
        if(player == null)
        {
            songLabel.setText("Choose a song first ffs!");
        }
        else {
            if (isPlaying) {
                player.pause();
                playButton.setText("▶");
                isPlaying = false;
                return;
            }
            player.play();
            playButton.setText("⏸");
            isPlaying = true;
        }
    }

    private File chooseAudioFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose a song");
        fileChooser.setInitialDirectory(new File("F://Downloads"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files","*.mp3"));
        return fileChooser.showOpenDialog(browser.getScene().getWindow());
    }

    private MediaPlayer createMediaPlayer(File file){
        if(player != null)
        {
            if(player.getStatus() != MediaPlayer.Status.DISPOSED){
                player.stop();
                player.dispose();
            }
        }
        MediaPlayer newPlayer = new MediaPlayer(new Media(FileUtils.fileToURI(file)));
        newPlayer.setVolume(volumeSlider.getValue()/100.0);
        newPlayer.setOnReady(()->{
                    length.setText(TimeUtils.dateToStringReady(newPlayer.getTotalDuration()));
                    seekSlider.setMax(newPlayer.getTotalDuration().toSeconds());
                }
        );
        return newPlayer;
    }

    @FXML
    protected void browseSong(){
        File choosenFile = chooseAudioFile();
        if(choosenFile != null){
            songLabel.setText(choosenFile.getName());
            player = createMediaPlayer(choosenFile);


                player.currentTimeProperty().addListener((obs,oldVal,newVal)->currLength.setText(TimeUtils.dateToStringReady(newVal)));
                seekSlider.setMin(0);
                player.currentTimeProperty().addListener((observableValue, duration, t1) ->{
                    if(!isSeeking){
                    seekSlider.setValue(t1.toSeconds());
                    }
                });


                isPlaying = false;
                onPlayButtonClick();




        }

    }

}