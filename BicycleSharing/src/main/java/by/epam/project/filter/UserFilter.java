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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.project.action.command.RoutePath;
import by.epam.project.entity.user.RoleType;
import by.epam.project.util.Constants;

@WebFilter(filterName = "UserFilter", urlPatterns = { "/jsp/user/*" }, dispatcherTypes = { DispatcherType.FORWARD,
        DispatcherType.REQUEST })
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        RoleType role = (RoleType) request.getSession().getAttribute(Constants.SESSION_ROLE);
        if (role != RoleType.USER) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(RoutePath.INDEX_PAGE_PATH.getRoutePath());
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}