package controller.command;

import model.entity.User;
import model.util.Constants;
import model.util.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegForward implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Role role = ((User) request.getSession().getAttribute(Constants.SESSION_USER))
                .getRole();
        String roleValue = role.getValue();
        return
                "forward:/WEB-INF/" + roleValue + "/registration.jsp";
    }
}
