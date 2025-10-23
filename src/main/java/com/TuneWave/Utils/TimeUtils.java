package com.TuneWave.Utils;

import javafx.util.Duration;

public class TimeUtils {

    public static String dateToStringReady(Duration duration){

        int minutes = (int) duration.toSeconds()/60;
        int seconds = (int) duration.toSeconds()%60;

        String readyMinutes = minutes<10 ? "0"+minutes : Integer.toString(minutes);
        String readySeconds = seconds<10 ? "0"+seconds : Integer.toString(seconds);

        return readyMinutes + ":" + readySeconds;


    }

}
