package util.engine;

import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.listener.ProcessListener;
import org.zeroturnaround.exec.stream.LogOutputStream;
import util.speech.SPTCommands;

public class SpeechEngine extends Engine {
	
    private Process speechRecognitionProcess;
	private ProcessExecutor executor;

	public SpeechEngine() {
		super(100);
		executor = new ProcessExecutor().command("python", "res/scripts/python/speech/speechin.py")
				.redirectOutput(new LogOutputStream() {
					@Override
					protected void processLine(String line) {
						processCommand(line);
					}
				});
		executor.addListener(new ProcessListener() {
			@Override
			public void afterStart(Process process, ProcessExecutor executor) {
				speechRecognitionProcess = process;
			}
		});
	}

	@Override
	public void start() {
		super.start();
		try {
            executor.execute();
        } catch (Exception e) {
            e.printStackTrace();
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
		//TODO check if script is running and restart if not
	}

	private void processCommand(String line) {
		line=line.trim();
		System.out.println("A: "+line);
		if (line.startsWith("warn(")) return;
		if (line.startsWith("C:")) return;
		if (line.startsWith("ERROR")) return;
		if (line.length() > 0) {
			SPTCommands.executeCommand(line);
		}
	}
}
