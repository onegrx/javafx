package pl.edu.agh.iisg.to.javafx.cw3.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class CommandRegistry {

	private ObservableList<Command> commandStack = FXCollections
			.observableArrayList();

    private List<Command> revertedCommands = new ArrayList<>();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
	}

	public void redo() {
        if(!revertedCommands.isEmpty()) {
            Command lastRevertedCommand = revertedCommands.get(revertedCommands.size() - 1);
            revertedCommands.remove(lastRevertedCommand);
            commandStack.add(lastRevertedCommand);
            lastRevertedCommand.redo();
        }
	}

	public void undo() {
        if(!commandStack.isEmpty()) {
            Command lastCommand = commandStack.get(commandStack.size() - 1);
            commandStack.remove(lastCommand);
            revertedCommands.add(lastCommand);
            lastCommand.undo();
        }
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}
}
