package util.speech;

import java.io.File;
import javax.sound.sampled.*;

public class Speaker {

    public static void speak(String words) {
        words = words.replaceAll("[^a-zA-Z0-9_-]", "");
        String filename = convertToFileName(words);
        try {
            if (!new File(filename).exists()) {
                ProcessBuilder pb = new ProcessBuilder("python", "res/scripts/python/speech/speaker.py", words);
                Process create = pb.start();
                create.waitFor();
                play(filename);
                create.destroy();
            } else {
                play(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void play(String filename) throws Exception {
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
        File file = new File(filename);
        stream = AudioSystem.getAudioInputStream(file);
        format = stream.getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000);
        clip.close();
        stream.close();
    }

    private static String convertToFileName(String words) {
        String filename = "res/scripts/python/speech/temp/";
        return filename + words + ".wav";
    }
}