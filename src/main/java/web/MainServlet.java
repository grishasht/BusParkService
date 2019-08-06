package web;

import controller.command.*;
import model.util.Constants;
import model.util.LogGenerator;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    Map<String, Command> commandMap = new HashMap<>();
    private Logger log = LogGenerator.getInstance();

    @Override
    public void init(ServletConfig config) {

        commandMap.put("/", new Home());
        commandMap.put("/login", new Login());
        commandMap.put("/logout", new Logout());
        commandMap.put("/reg_fwd", new RegForward());
        commandMap.put("/register", new Register());

        log.info("Servlet was initted");
        //getServletContext().setAttribute(USER_LIST, new LinkedList<User>());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request, response);
        log.info("Servlet get method executed");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request, response);
        log.info("Servlet post method executed");
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = request.getPathInfo();
        request.setAttribute(Constants.CUR_REQ_URL, request.getRequestURL());
        Command command = commandMap.get(path);
        if (command != null) {
            String page = command.execute(request, response);

            if (page.contains("redirect")) {
                response.sendRedirect(page.replace("redirect:", ""));
                log.info("Redirect to " + page.replace("redirect:", ""));
            } else if (page.contains("forward")) {
                request.getRequestDispatcher(page.replace("forward:", ""))
                        .forward(request, response);
                log.info("Forward to " + page.replace("redirect:", ""));
            }
        } else {
            log.info("In MainServlet command == null");
        }
    }
}
