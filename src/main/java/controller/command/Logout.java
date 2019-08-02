package controller.command;

import model.entity.User;
import model.util.Constants;
import model.util.LogGenerator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Command {
    private Logger log = LogGenerator.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        User guest = User.getGuest();
        request.getSession().setAttribute(Constants.SESSION_USER, guest);
        String curLang = (String) request.getSession().getAttribute(Constants.CUR_LANG);
        log.info("User " + user.getEmail() + " logged out");

        return "redirect:/page/?curLang=" + curLang;
    }
}
