package controller.command;

import model.dao.impl.ConnectionPool;
import model.entity.User;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoManageBuses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String role = ((User) request.getSession().getAttribute(Constants.SESSION_USER)).getRole().toString();
        return "forward:/WEB-INF/" + role + "/buses.jsp";
    }
}
