package util.io;

import util.structures.List;
import util.structures.Map;

public class Load {
    
    public static Map<String, String> loadCFG(String name) {
        Map<String, String> data = new Map<>();
        List<String> lines = FileIO.read("res/config/" + name + ".cfg");
        for (String line : lines) {
            String[] parts = line.split("=");
            data.put(parts[0], parts[1]);
        }
        return data;
    }

    public static List<String> loadData(String name) {
        return FileIO.read("res/data/" + name + ".dat");
    }
}
