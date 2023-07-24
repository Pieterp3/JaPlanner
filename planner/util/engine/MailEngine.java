package util.engine;

public class MailEngine extends Engine {

	public MailEngine(int checksPerMinute) {
		super(checksPerMinute / 60 * 1000);
		//TODO connect to the mail server
	}

	public MailEngine() {
		this(60);
	}

	

	@Override
	protected void execute() {
		// TODO check the mail server for new mail
		throw new UnsupportedOperationException("Unimplemented method 'execute'");
	}

}
