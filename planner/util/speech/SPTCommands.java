package util.speech;

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

	public static void executeCommand(String command) {
		String[] args = command.split(" ");
		switch (args[0]) {
			case "open":
				openSomething(args);
			break;
			case "close":
			case "exit":
			case "quit":
				closeSomething(args);
			break;
			default:
				System.out.println("Command not found: " + command);
				Speech.speak("Command not found: " + command);
			break;
		}
	}

	private static void closeSomething(String[] args) {
		if (args.length == 1) {
			System.out.println("Cannot close nothing.");
			return;
		}
		Speech.speak("Closing " + args[1] + ".");
		switch (args[1]) {
			case "kodi":
			case "mozilla":
			case "firefox":
				try {
					Runtime.getRuntime().exec("taskkill /F /IM " + args[1] + ".exe");
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
				System.out.println("Cannot close: " + args[1]);
				Speech.speak("Cannot close: " + args[1]);
				break;
		}
	}

	private static void openSomething(String[] args) {
		if (args.length == 1) {
			System.out.println("Cannot open nothing.");
			return;
		}
		Speech.speak("Opening " + args[1] + ".");
		switch (args[1]) {
			case "kodi":
			case "mozilla":
			case "firefox":
			case "roblox":
				try {
					Runtime.getRuntime().exec(openProcesses.get(args[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			default:
				System.out.println("Cannot open: " + args[1]);
				Speech.speak("Cannot open: " + args[1]);
				break;
		}
	}
}
