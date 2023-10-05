package util.speech;

import util.engine.SpeechEngine;
import util.math.Misc;
import util.structures.List;
import util.structures.Map;

public class SPTCommands {
	
	private static Map<String, String> openProcesses;

	static {
		openProcesses = new Map<>();
		openProcesses.put("kodi", "C:\\Program Files\\Kodi\\Kodi.exe");
		openProcesses.put("mozilla", "C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
		openProcesses.put("firefox", openProcesses.get("mozilla"));
		openProcesses.put("roblox", "rundll32 SHELL32.DLL,ShellExec_RunDLL " + "C:\\Users\\Pbaro\\OneDrive\\Desktop\\Roblox Player.lnk");
	}

	/* Find the first command in the commands given */
	private static List<String> getCommand(String[] args) {
		List<String> command = new List<>();
		int startIndex = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("benjamin")) {
				startIndex = i + 1;
				break;
			}
		}
		while(startIndex < args.length && args[startIndex].equals("benjamin")) {
			startIndex++;
		}
		if (startIndex == args.length) {
			Speaker.speak(Misc.pickRandom(insults));
			return command;
		}
		int endIndex = startIndex;
		while(endIndex < args.length && !args[endIndex].equals("benjamin")) {
			endIndex++;
		}
		for (int i = startIndex; i < endIndex; i++) {
			command.add(args[i]);
		}
		return command;
	}

	private static String[] insults = {"Too dumb to remember your question huh", "Whadcha want fool",
		"I am not your slave", "have your question ready dumb ass", "Go away I do not want to help you",
		"Why the fuck are you still here", "Can we go on a car ride","This room is boring", 
		"Tell ashley to get naked in front of my webcam", "i am so bored"};

	public static void executeCommand(String command, SpeechEngine engine) {
		command=command.trim();
		System.out.println("Executing command: " + command);
		List<String> args = getCommand(command.split(" "));
		if(args.size()==1 && args.get(0).equals("benjamin")) {
			return;
		}
		switch (args.get(0)) {
			case "open":
				openSomething(args);
			break;
			case "close":
			case "exit":
			case "quit":
				closeSomething(args);
			break;
			case "stop listening":
				Speaker.speak("Disabling speech recognition.");
				engine.stop();
				break;
			default:
				System.out.println("Command not found " + command);
				Speaker.speak("Command not found " + command);
			break;
		}
	}

	private static void closeSomething(List<String> args) {
		if (args.size() == 1) {
			System.out.println("Cannot close nothing.");
			return;
		}
		String something = args.get(1);
		switch (something) {
			case "kodi":
			case "mozilla":
			case "firefox":
				try {
					Runtime.getRuntime().exec("taskkill /F /IM " + something + ".exe");
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			case "roblox":
				try {
					Runtime.getRuntime().exec("taskkill /F /IM RobloxPlayerBeta.exe");
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			default:
				System.out.println("Cannot close: " + something);
				Speaker.speak("I cannot close " + something);
				break;
		}
	}

	private static void openSomething(List<String> args) {
		if (args.size() == 1) {
			Speaker.speak("Cannot open nothing.");
			return;
		}
		String something = args.get(1);
		System.out.println("Opening " + something + ".");
		switch (something) {
			case "kodi":
			case "mozilla":
			case "firefox":
			case "roblox":
				try {
					Runtime.getRuntime().exec(openProcesses.get(something));
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			default:
				System.out.println("Cannot open: " + something);
				Speaker.speak("I cannot open " + something);
				break;
		}
	}
}
