package managers;

import ui.Frame;

import java.util.List;
import java.util.ArrayList;

public class ActionHandler {
    
    private Frame frame;
    private List<String> actions;
    private List<Command> commands;

    public ActionHandler(Frame frame) {
        this.frame = frame;
        actions = new ArrayList<String>();
        commands = new ArrayList<Command>();
        createActionCommands();
    }

    private void createActionCommands() {
        commands.add(new Command("exit") {
            @Override
            public boolean execute() {
                System.exit(0);
                return true;
            }
        });
        commands.add(new Command("help") {
            @Override
            public boolean execute() {
                System.out.println("Commands:");
                for (Command c : commands) {
                    System.out.println(c.command);
                }
                return true;
            }
        });
    }

    public void processAction(String command) {
        System.out.println("Processing action: " + command);
        actions.add(command);
        for (Command c : commands) {
            if (c.isCommand(command)) {
                if (c.execute()) {
                    return;
                }
            }
        }
        frame.getActivePanel().processAction(command);
    }

    public Frame getFrame() {
        return frame;
    }

    public List<String> getActions() {
        return actions;
    }

    public void clearActions() {
        actions.clear();
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public abstract class Command {
        final String command;
        
        public Command(String command) {
            this.command = command;
        }

        public boolean isCommand(String command) {
            return this.command.equalsIgnoreCase(command.trim());
        }

        public abstract boolean execute();
    }

}
