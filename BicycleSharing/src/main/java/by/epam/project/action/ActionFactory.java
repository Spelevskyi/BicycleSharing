package by.epam.project.action;

import java.util.Optional;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.CommandType;

public class ActionFactory {
    public static Optional<ActionCommand> defineCommand(String command) {
        System.out.println(command.toUpperCase());
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        return (Optional.of(commandType.getCurrentCommand()));
    }
}
