package floorplanning.display.panels;

import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.frames.Frame;
import util.events.Event;
import util.events.EventTimer;

/**
 * Displays a screen which says 'welcome'
 * 
 * Gives the user an input for a password
 * 
 * If the password is correct, the user is taken to the main screen
 * 
 * 
 */
public class WelcomePanel extends Panel {

	private Label welcomeLabel;
	private UserInput passwordInput;
	private Button loginButton;
	private HomePanel homePanel;

	public WelcomePanel(Frame frame) {
		super(frame, "floorplan welcome");
	}

	@Override
	public void update() {
		passwordInput.setAttribute("width", getWidth() - 20);
		loginButton.setAttribute("width", getWidth() - 20);
	}

	@Override
	protected void init() {
		welcomeLabel = new Label(getFrame(), "Welcome to the Floorplanning Program!", 0, 10, getWidth(), 25);
		welcomeLabel.setAttribute("alignment", "center");
		addComponent(welcomeLabel);
		passwordInput = new UserInput(getFrame(), "", 10, 60, getWidth() - 20, 40, "Password");
		passwordInput.setAttribute("alignment", "center");
		passwordInput.setAttribute("password", "true");
		addComponent(passwordInput);
		loginButton = new Button(getFrame(), "Login", 10, 110, getWidth() - 20, 40);
		loginButton.setAttribute("alignment", "center");
		loginButton.setAttribute("action", "login");
		passwordInput.setActionButton(loginButton);
		addComponent(loginButton);
	}

	@Override
	public void handleAction(String command) {
		if (command.equals("login")) {
			if (!passwordInput.getText().equalsIgnoreCase("password")) {
				loginButton.setAttribute("color", "ff0000");
				loginButton.setAttribute("hoverColor", "ff0000");
				passwordInput.setText("");
				getFrame().addTimer(new EventTimer(new Event() {

					@Override
					public void execute() {
						loginButton.setAttribute("color", "000000");
						loginButton.setAttribute("hoverColor", "000000");
					}

				}, 1500, false));
			} else if (homePanel == null) {
				homePanel = new HomePanel(getFrame(), this);
				getFrame().setActivePanel(homePanel);
			} else {
				getFrame().setActivePanel(homePanel);
			}
		}
	}

	@Override
	public void keyTyped(int c) {}

	@Override
	public void preComponentDrawing() {}

	@Override
	public void finishPanelDrawing() {}

}
