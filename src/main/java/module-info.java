module com.example.mediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens com.TuneWave to javafx.fxml;
    exports com.TuneWave;
}