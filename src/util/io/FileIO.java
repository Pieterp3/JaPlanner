package util.io;

import java.io.*;
import util.structures.lists.List;
/**
 * TODO
    a. Allow for SQL, JSON, TXT and xml
 */
public class FileIO {

    public static void write(String file, String[] lines) {
        if (file == null || lines == null) {
            return;
        }
        File f = new File(file);
        try (PrintWriter pw = new PrintWriter(f)) {
            for (String line : lines) {
                pw.println(line);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> read(String file) {
        List<String> lines = new List<>();
        if (file == null) {
            return lines;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 0)
                    lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: Could not read file: " + file);
        }
        return lines;
    }

    public static void deleteFile(String string) {
        File file = new File(string);
        if (file.exists()) {
            file.delete();
        }
    }

}
