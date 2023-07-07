

import ui.Frame;
import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.ComponentList;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.components.impl.shapes.impl.Rectangle;

public class HomePanel extends Panel {

    public HomePanel(Frame frame) {
        super(frame, "home");
    }
    
    protected void init() {
        Label label = new Label(getFrame(), "Peerless Estimator", 0, 20);
        label.setAttribute("width", getWidth());
        label.setAttribute("alignment", "center");
        label.setAttribute("fontSize", 4);
        addComponent(label);
        Button button = new Button(getFrame(), "Click me", 10, 50, 200, 40);
        button.setAttribute("alignment", "center");
        button.setAttribute("action", "help");
        addComponent(button);
        ComponentList list = new ComponentList(getFrame(), 10, 100, 200, 200);
        for(int i = 0;i<10;i++) {
            Button b = new Button(getFrame(), "Button " + i, 0, 0, 200, 35);
            list.addComponent(b);
        }
        addComponent(list);
        UserInput input = new UserInput(getFrame(), "", 10, 310, 200, 40, "Placeholder");
        addComponent(input);
        Rectangle rect = new Rectangle(getFrame(), 10, 360, 200, 40);
        rect.setAttribute("backgroundColor", "ff0000");
        //addComponent(rect);
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
    public void handleAction(String command) {
        System.out.println("Processing action: '" + command + "' in home panel");
    }

    @Override
    public void preComponentDrawing() {
        
    }

    @Override
    public void finishPanelDrawing() {
        
    }
    
}
