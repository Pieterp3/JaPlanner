package botting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import util.structures.lists.List;

public class ColorBot {

    public static void main(String[] args) throws IOException {
        String line;
        Process p = Runtime.getRuntime().exec("tasklist.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        List<String> processes = new List<>();
        while ((line = input.readLine()) != null) {
            processes.add(line);
        }
        processes.sort();
        for (String process : processes) {
            System.out.println(process);
        }
        input.close();
    }

}
