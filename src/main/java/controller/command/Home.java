package controller.command;

import model.entity.User;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Home implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> role = Optional.ofNullable((User)request.getSession().getAttribute(Constants.SESSION_USER));

        System.out.println(request.getQueryString());
        return "forward:/WEB-INF/" + role.orElse(User.getGuest()).getRole().toString() + "/index.jsp"
                + "?curLang=" + request.getSession().getAttribute(Constants.CUR_LANG);
    }
}
