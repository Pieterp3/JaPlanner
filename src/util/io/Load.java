package util.io;

import java.io.File;
import util.structures.Map;
import util.structures.lists.List;

public class Load {

    public static Map<String, String> loadCFG(String name) {
        Map<String, String> data = new Map<>();
        if (!new File("res/config/" + name + ".cfg").exists()) {
            System.out.println("Error: Could not locate config file: " + name + ".cfg");
            return data;
        }
        List<String> lines = FileIO.read("res/config/" + name + ".cfg");
        for (String line : lines) {
            String[] parts = line.split("=");
            data.put(parts[0], parts[1]);
        }
        return data;
    }

    public static List<String> loadData(String name) {
        try {
            return FileIO.read("res/data/" + name + ".dat");
        } catch (Exception e) {
            System.out.println("Error: Could not locate data file: " + name + ".dat");
            return new List<>();
        }
    }

    public static String loadSpeechResult(String name) {
        return FileIO.read(name).get(0);
    }
}
