package com.TuneWave.Utils;

import java.io.File;
import java.net.URI;

public class FileUtils {

    public static String fileToURI(File file){
        URI uri = file.toURI();

        return uri.toString();
    }

    public static String fileToString(File file){
        String name = file.toString();
        return name;
    }

//    public static List extractAllSongsInFolder(File folder){
//        to be implemented soon
//
//    }
}
