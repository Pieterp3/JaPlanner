
import ui.Frame;
import ui.Panel;
import ui.components.containers.impl.ComponentList;
import ui.components.containers.impl.ItemChooser;
import ui.components.shapes.Polygon;
import ui.components.shapes.impl.*;
import ui.components.impl.*;
import ui.graphics.Color;
import ui.graphics.fonts.impl.DefaultChar;
import util.events.EventTimer;
import util.math.Misc;
import util.events.Event;
import util.structures.Map;

public class HomePanel extends Panel {

    public HomePanel(Frame frame) {
        super(frame, "home");
    }

    protected void init() {
        addOrbiters();

        String text = "";
        for (DefaultChar c : DefaultChar.values()) {
            text += c.getCharacter();
        }
        int step = 10;
        for (int i = 0; i < text.length(); i += step) {
            int cap = Math.min(i + step, text.length());
            String disText = "";
            for (int j = i; j < cap; j++) {
                disText += text.charAt(j) + " ";
            }
            Label l = new Label(getFrame(), disText.trim(), 10, 10 + (i / step) * 40);
            l.setAttribute("width", getWidth());
            l.setAttribute("fontSize", 2);
            addComponent(l);
        }

        Button button = new Button(getFrame(), "Click me", 10, 50, 200, 40);
        button.setAttribute("alignment", "center");
        button.setAttribute("action", "help");
        // addComponent(button);
        ComponentList list = new ComponentList(getFrame(), getWidth() - 220, 10, 200, 450);
        for (int i = 0; i < 10; i++) {
            Button b = new Button(getFrame(), "Button " + i, 0, 0, 200, 35);
            list.addComponent(b);
        }
        addComponent(list);
        UserInput input = new UserInput(getFrame(), "", getWidth() - 220, 470, 200, 40, "Placeholder");
        addComponent(input);

        ItemChooser chooser = new ItemChooser(getFrame(), 250, 10, 300, 25);
        for (int i = 0;i<10;i++) {
            chooser.addComponent(new Button(getFrame(),"Item " + i, 0, 0, 0, 15));
        }
        addComponent(chooser);
    }

    private void addOrbiters() {
        Polygon rect = new Circle(getFrame(), getWidth() / 2 - 25, getHeight() / 2 - 25, 15);
        rect.setAttributes(new Map<String, Object>() {
            {
                put("borderColor", "000000");
                put("borderWidth", "2");
                put("backgroundColor", "00ff00");
            }
        });
        Event event = new Event() {
            @Override
            public void execute() {
                rect.getStyle().setColorAttribute("backgroundColor", Color.randomDefaultColor());
            }
        };
        EventTimer timer = new EventTimer(event, 2500, true);
        getFrame().addTimer(timer);
        getFrame().scheduleEvent(() -> {
            rect.rotate(.5);
        }, 2, true);
        addComponent(rect);

        LoadingBar bar = new LoadingBar(getFrame(), "Changing color...", 10, getHeight() - 70, getWidth() - 40, 20);
        bar.setEventTimer(timer);
        addComponent(bar);

        double distance = 25;
        double subDistance = 0;
        for (int i = 0; i < 10; i++) {
            double bigSize = 8 + Misc.getRandomNumber(2, 4);
            Polygon p = createOrbiter(rect, distance, bigSize);
            distance += 6.0;
            if (Misc.randomBoolean()) {
                subDistance = bigSize;
                for (int j = 0; j < Misc.getRandomNumber(4, 7); j++) {
                    double size = 6 + Misc.getRandomNumber(1, 3);
                    double d2 = subDistance + (j * 8) + 8;
                    createOrbiter(p, d2, size);
                    subDistance += d2;
                }
            }
        }
    }

    private Polygon createOrbiter(Polygon orbit, double distance, double size) {
        int x = orbit.getX();
        int y = orbit.getY();
        if (Misc.randomBoolean())
            x += distance;
        else
            x -= distance;
        if (Misc.randomBoolean())
            y += distance;
        else
            y -= distance;
        final Polygon r = new Triangle(getFrame(), x, y, size, size);
        r.setAttributes(new Map<String, Object>() {
            {
                put("borderColor", "000000");
                put("borderWidth", "1");
                put("backgroundColor", "000000");
            }
        });
        r.getStyle().setColorAttribute("backgroundColor", Color.randomDefaultColor());
        getFrame().scheduleEvent(
                new Event() {
                    double angle = 0;
                    boolean clockwise = Misc.randomBoolean();
                    boolean rotClockwise = Misc.randomBoolean();
                    double rotSpeed = Misc.getRandomNumber(.2, .8);
                    double orbitSpeed = Misc.getRandomNumber(.4, 1.1);

                    @Override
                    public void execute() {
                        if (!rotClockwise)
                            r.rotate(-rotSpeed);
                        else
                            r.rotate(rotSpeed);
                        if (clockwise)
                            r.moveInArc(orbit, angle += orbitSpeed % 360, distance);
                        else {
                            angle -= orbitSpeed;
                            if (angle < 0)
                                angle = 360 - angle;
                            r.moveInArc(orbit, angle, distance);
                        }
                    }
                },
                2, true);
        addComponent(r);
        return r;
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
