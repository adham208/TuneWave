module com.example.mediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.TuneWave to javafx.fxml;
    exports com.TuneWave;
}