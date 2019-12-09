package by.epam.project.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.project.action.command.RoutePath;
import by.epam.project.entity.user.RoleType;
import by.epam.project.util.Constants;

@WebFilter(filterName = "SessionFilter", urlPatterns = { "/*" }, dispatcherTypes = { DispatcherType.REQUEST,
        DispatcherType.FORWARD, DispatcherType.INCLUDE })
public class SessionFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().isNew()) {
            if (request.getServletPath().equals(RoutePath.INDEX_PAGE_PATH.toString())) {
                HttpSession session = request.getSession(true);
                session.setAttribute(Constants.LANGUAGE, "en");
                session.setAttribute(Constants.SESSION_IS_LOGIN, Constants.FALSE);
                session.setAttribute(Constants.SESSION_ROLE, RoleType.GUEST);
            }
        } else {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(RoutePath.INDEX_PAGE_PATH.toString());
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}