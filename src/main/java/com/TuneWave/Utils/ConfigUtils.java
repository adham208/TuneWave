package com.TuneWave.Utils;

import com.TuneWave.model.Config;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigUtils {

    public static Config getDefaultFolder() throws IOException {
        String path = System.getProperty("user.home") + "/TuneWave/config.json";
        File file = new File(path);

        if(file.exists())
        {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(file);
            Config config = gson.fromJson(fileReader,Config.class);
            return config;
        }else{
            return null;
        }

    }
}
