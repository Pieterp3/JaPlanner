package io;

import structures.Map;

public class Save {
    
    public static void saveConfigFile(String file, Map<String, String> data) {
        file = "res/config/" + file + ".cfg";
        if (file == null || data == null) {
            return;
        }
        String[] lines = new String[data.size()];
        int i = 0;
        for (String key : data.getKeys()) {
            lines[i] = key + "  " + data.get(key);
            i++;
        }
        FileIO.write(file, lines);
    }

}
