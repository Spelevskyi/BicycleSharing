package by.epam.project.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.project.action.ActionFactory;
import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;


@WebServlet("/controller")

public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        processRequest(request, response);
	}

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<ActionCommand> definedCommand = ActionFactory.defineCommand(req.getParameter("command"));
        Router router = definedCommand.get().execute(req);
        String pagePath = router.getRoutePath();
        if (router.getType().equals(RouteType.FORWARD)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(pagePath);
        }
    }

}
