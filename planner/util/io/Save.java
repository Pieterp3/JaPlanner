package util.io;

import structures.Map;
import structures.List;

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

    public static void saveDataFile(String file, List<String> data) {
        file = "res/data/" + file + ".dat";
        FileIO.deleteFile("file");
        if (file == null || data == null) {
            return;
        }
        String[] lines = new String[data.size()];
        int i = 0;
        for (String line : data) {
            lines[i] = line;
            i++;
        }
        FileIO.write(file, lines);
    }

}
