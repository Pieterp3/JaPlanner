

import ui.Frame;
import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.ComponentList;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.components.impl.shapes.impl.Rectangle;
import ui.graphics.fonts.impl.DefaultChar;
import util.structures.Map;

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

        String text = "";
        for (DefaultChar c : DefaultChar.values()) {
            text += c.getCharacter();
        }
        int step = 10;
        for (int i = 0;i<text.length();i += step) {
            int cap = Math.min(i + step, text.length());
            String disText = "";
            for (int j = i;j<cap;j++) {
                disText += text.charAt(j) + " ";
            }
            Label l = new Label(getFrame(), disText.trim(), 0, 60 + (i / step) * 40);
            l.setAttribute("width", getWidth());
            l.setAttribute("alignment", "center");
            l.setAttribute("fontSize", 4);
           // addComponent(l);
        }
        

        Button button = new Button(getFrame(), "Click me", 10, 50, 200, 40);
        button.setAttribute("alignment", "center");
        button.setAttribute("action", "help");
       // addComponent(button);
        ComponentList list = new ComponentList(getFrame(), 10, 100, 200, 200);
        for(int i = 0;i<10;i++) {
            Button b = new Button(getFrame(), "Button " + i, 0, 0, 200, 35);
            list.addComponent(b);
        }
       // addComponent(list);
        UserInput input = new UserInput(getFrame(), "", 10, 310, 200, 40, "Placeholder");
       // addComponent(input);
        Rectangle rect = new Rectangle(getFrame(), 375, 275, 50, 50);
        rect.setAttributes(new Map<String, Object>() {{
                put("borderColor", "000000");
                put("borderWidth", "2");
                put("backgroundColor", "00ff00");
            }});
        getFrame().scheduleEvent(() -> {
            rect.rotate(10);
        }, 250);
        addComponent(rect);
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
