package ui.panels;

import ui.Frame;
import ui.panels.components.impl.Button;
import ui.panels.components.impl.ComponentList;
import ui.panels.components.impl.Label;
import ui.panels.components.impl.TextInput;

import java.awt.Graphics2D;

public class HomePanel extends Panel {

    public HomePanel(Frame frame) {
        super(frame, "home");
    }
    
    protected void init() {
        Label label = new Label(getFrame(), "Peerless Estimator", 0, 20);
        label.getStyle().setWidth(getWidth());
        label.getStyle().setAlignment("center");
        label.getStyle().setFont("Arial", -1, 16);
        addComponent(label);
        Button button = new Button(getFrame(), "Click me", 10, 50, 200, 40);
        button.getStyle().setAlignment("center");
        button.getStyle().setAction("help");
        addComponent(button);
        ComponentList list = new ComponentList(getFrame(), 10, 100, 200, 200);
        for(int i = 0;i<10;i++) {
            Button b = new Button(getFrame(), "Button " + i, 0, 0, 200, 35);
            list.addComponent(b);
        }
        addComponent(list);
        TextInput input = new TextInput(getFrame(), "", 10, 310, 200, 40, "Placeholder");
        addComponent(input);
    }

    @Override
    public void update() {
       // System.out.println("Updating home panel");
    }

    @Override
    public void keyTyped(int keyCode) {
        System.out.println("HomePanel Key typed: " + keyCode);
    }

    @Override
    public void processAction(String command) {
        System.out.println("Processing action: '" + command + "' in home panel");
    }

    @Override
    public void preComponentDrawing(Graphics2D g) {
        
    }

    @Override
    public void finishPanelDrawing(Graphics2D g) {
        
    }
    
}
