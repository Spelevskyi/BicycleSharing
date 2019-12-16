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
import by.epam.project.pool.ConnectionPool;
import by.epam.project.util.Constants;


@WebServlet("/controller")

public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setMaxInactiveInterval(10 * 60);
        Optional<ActionCommand> definedCommand = ActionFactory.defineCommand(req.getParameter(Constants.COMMAND));
        Router router = definedCommand.get().execute(req);
        String pagePath = router.getRoutePath();
        if (router.getType().equals(RouteType.FORWARD)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(pagePath);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.destroyPool();
    }

}
