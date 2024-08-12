package uobject.command.macro;

import uobject.command.Command;
import uobject.error.exception.MacroCommandException;

import java.util.Queue;

public class MacroCommand implements Command {

    private final Queue<Command> queue;

    public MacroCommand(Queue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void execute() {
        try {
            while (queue.peek() != null) {
                queue.peek().execute();
                queue.remove();
            }
        } catch (Exception e) {
            throw new MacroCommandException(this, e);
        }
    }
}
