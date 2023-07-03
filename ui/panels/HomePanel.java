package ui.panels;

import ui.panels.components.Button;
import ui.Frame;
import ui.Panel;
import ui.panels.components.Label;
import java.awt.Graphics2D;
import ui.panels.components.style.*;

public class HomePanel extends Panel {

    public HomePanel(Frame frame) {
        super(frame, "home");
    }
    
    protected void init() {
        Label label = new Label(getFrame(), "Peerless Estimator", getWidth(), 10);
        label.getStyle().setAlignment("center");
        label.getStyle().setFont("Arial", -1, 16);
        addComponent(label);
        Button button = new Button(getFrame(), "Click me", 10, 50, 200, 40);
        button.getStyle().setAlignment("center");
        button.getStyle().setAction("help");
        addComponent(button);
    }

    @Override
    public void update() {
       // System.out.println("Updating home panel");
    }

    @Override
    public void keyTyped(int keyCode) {
        System.out.println("Key typed: " + keyCode);
    }

    @Override
    public void processAction(String command) {
        System.out.println("Processing action: " + command + " in home panel");
    }

    @Override
    public void preComponentDrawing(Graphics2D g) {
        
    }

    @Override
    public void finishPanelDrawing(Graphics2D g) {
        
    }
    
}
