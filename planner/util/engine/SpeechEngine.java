package util.engine;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.listener.ProcessListener;
import org.zeroturnaround.exec.stream.LogOutputStream;
import util.speech.SPTCommands;

public class SpeechEngine extends Engine {

	private Process speechRecognitionProcess;
	private ProcessExecutor executor;
	private ListeningStates listeningState = ListeningStates.STARTING;
	private String lastResponse = "";

	public SpeechEngine() {
		super(1000);
		executeListener();
	}

	private void executeListener() {
		executor = new ProcessExecutor().command("python", "res/scripts/python/speech/speechin.py")
				.redirectOutput(new LogOutputStream() {
					@Override
					protected void processLine(String line) {
						System.out.println("Raw line: " + line);
						line = line.trim();
						boolean newState = false;
						for (ListeningStates states : ListeningStates.values()) {
							for (String response : states.responsesFromScript) {
								if (line.equals(response)) {
									listeningState = states;
									newState = true;
								}
							}
						}
						if (newState) {
							if (ListeningStates.DONE.equals(listeningState))
								processCommand(lastResponse);
							if (Arrays.asList(new ListeningStates[] {ListeningStates.DONE, ListeningStates.NONE}).contains(listeningState)) {
								stop();
								try {
									Thread.sleep(100);
								} catch (Exception e) {
								}
								start();
							}
						}
						lastResponse = line;
						executeListener();
					}
				});
		executor.addListener(new ProcessListener() {
			@Override
			public void afterStart(Process process, ProcessExecutor executor) {
				speechRecognitionProcess = process;
			}
		});
	}

	public String getSpeechState() {
		return listeningState.answerForMonitor;
	}

	@Override
	public void start() {
		super.start();
		try {
			executor.execute();
		} catch (TimeoutException e) {
			listeningState = ListeningStates.NONE;
			stop();
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
			start();
		} catch (Exception e) {

		}
	}

	@Override
	public void stop() {
		if (speechRecognitionProcess != null)
			speechRecognitionProcess.destroy();
		super.stop();
	}

	@Override
	public void execute() {

	}

	private void processCommand(String line) {
		if (line.length() > 0) {
			SPTCommands.executeCommand(line, this);
		}
	}

	enum ListeningStates {
		STARTING(new String[] { "<Starting>" }, "Getting warmed up!"), LISTENING(new String[] { "<Listening>" },
				"Listening for your command!"), RECORDING(new String[] { "<Recording>" },
						"Recording your every word..."), PROCESSING(new String[] { "<Processing>" },
								"Processing your statements..."), NONE(
										new String[] { "<None>", "warn(", "C:", "ERROR", "WARN" },
										"Buncha bs, lets try again!"), DONE(new String[] { "<Done>" },
												"All set begger? I'll be listening for you!");

		private String[] responsesFromScript;
		private String answerForMonitor;

		ListeningStates(String[] responsesFromScript, String answerForMonitor) {
			this.responsesFromScript = responsesFromScript;
			this.answerForMonitor = answerForMonitor;
		}
	}
}
