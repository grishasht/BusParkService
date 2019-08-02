package web.filters;

import model.entity.User;
import model.util.Constants;
import model.util.LogGenerator;
import model.util.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoleFilter implements Filter {
    private Map<Role, Set<String>> paths = new HashMap<>();
    private Logger log = LogGenerator.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Set<String> adminRef = new HashSet<>();
        Set<String> driverRef = new HashSet<>();
        Set<String> guestRef = new HashSet<>();

        adminRef.add("/");

        driverRef.add("/");
        driverRef.add("/reg_fwd");
        driverRef.add("/register");
        driverRef.add("/logout");

        guestRef.add("/");
        guestRef.add("/login");
        guestRef.add("/register");
        guestRef.add("/reg_fwd");

        paths.put(Role.ADMIN, adminRef);
        paths.put(Role.DRIVER, driverRef);
        paths.put(Role.GUEST, guestRef);

        log.info("Role filter was initted");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI()
                .replace(request.getContextPath(), "")
                .replace(request.getServletPath(), "");

        if (request.getSession().getAttribute(Constants.SESSION_USER) == null) {
            request.getSession().setAttribute(Constants.SESSION_USER, new User().getGuest());
        }

        log.info("User was set in session");

        Role sessionRole = ((User) request.getSession().getAttribute(Constants.SESSION_USER)).getRole();

        if (!paths.get(sessionRole).contains(requestURI)) {
            request.getRequestDispatcher("/WEB-INF/errors/not_found.jsp")
                    .forward(request, response);
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
