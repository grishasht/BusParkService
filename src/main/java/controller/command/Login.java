package controller.command;

import model.entity.User;
import model.service.UserService;
import model.util.Constants;
import model.util.LogGenerator;
import model.util.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Command {
    private UserService userService = new UserService();
    private Logger log = LogGenerator.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.identifyUser(login, password);

        userService.authorize(user, request);
        log.info("User " + user.getEmail() + " authorized");
        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG);

    }
}
