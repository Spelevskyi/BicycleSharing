package by.epam.project.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.project.entity.user.User;
import by.epam.project.util.Constants;

@WebFilter(filterName = "ConfirmedFilter", urlPatterns = { "/jsp/account.jsp", "/jsp/user/rental_point.jsp",
        "/jsp/user/favorites.jsp" }, dispatcherTypes = { DispatcherType.REQUEST,
                DispatcherType.FORWARD }, initParams = {
                        @WebInitParam(name = "MESSAGE_PATH", value = "/jsp/message.jsp") })
public class ConfirmedFilter implements Filter {

    private String messagePath;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        messagePath = fConfig.getInitParameter("MESSAGE_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User sessionUser = (User) httpRequest.getSession().getAttribute(Constants.SESSION_USER);
        if (!sessionUser.isConfirmed()) {
            httpRequest.getSession().setAttribute(Constants.ERROR, Constants.CONFIRMED_ERROR);
            RequestDispatcher dispatcher = httpRequest.getServletContext()
                    .getRequestDispatcher(messagePath);
            dispatcher.forward(httpRequest, httpResponse);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}