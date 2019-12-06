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

import by.epam.project.action.command.RoutePath;
import by.epam.project.entity.user.RoleType;
import by.epam.project.util.Constants;

@WebFilter(filterName = "AdminFilter", urlPatterns = { "/jsp/admin/*" }, dispatcherTypes = { DispatcherType.REQUEST,
        DispatcherType.FORWARD, DispatcherType.INCLUDE })
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        RoleType role = (RoleType) req.getSession().getAttribute(Constants.SESSION_ROLE);
        if (role != RoleType.ADMIN) {
            ((HttpServletResponse) servletResponse)
                    .sendRedirect(req.getContextPath() + RoutePath.INDEX_PAGE_PATH.getRoutePath());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
