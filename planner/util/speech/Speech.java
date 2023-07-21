package util.speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speech {

    static {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    }

    public static void speak(String words) {
        System.out.println("Speaking: " + words);
        System.setProperty("mbrola.base", "lib/freetts-mbrolla.jar");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoices()[1];
        System.out.println("Found voices: " + voiceManager.getVoices().length);
        if (voice == null) {
            System.err.println("Cannot find voice");
            System.exit(1);
        }
        System.out.println("Using voice: " + voice.getName());
        voice.allocate();
        voice.speak(words);
        voice.deallocate();
    }

}