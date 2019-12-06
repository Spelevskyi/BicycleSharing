package by.epam.project.action.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest request);
}
