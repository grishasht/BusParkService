package controller.command;

import model.entity.User;
import model.service.UserService;
import model.util.Constants;
import model.util.LogGenerator;
import model.util.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Register implements Command {
    private  UserService userService = new UserService();
    private Logger log = LogGenerator.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(Constants.CUR_LANG)));
        User user = new User();

        String name = request.getParameter(Constants.USERNAME);
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        String confirmPassword = request.getParameter(Constants.CONFIRM_PASSWORD);
        String age = request.getParameter(Constants.USR_AGE);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.DRIVER);
        user.setLogin(name);
        try {
            user.setAge(Integer.valueOf(age));
        } catch (Exception e){
            return "forward:/WEB-INF/errors/inv_input.jsp";
        }

        String validationInputErr = userService.validationInputErr(user, resourceBundle);
        String userPresentError = userService.userPresentError(user, resourceBundle);
        if (!"".equals(validationInputErr)){
//            userService.setResponse(400, validationInputErr, response);
            return "forward:/WEB-INF/errors/inv_input.jsp";
        } else if (!userService.confirmPassword(password, confirmPassword)){
//            userService.setResponse(400, resourceBundle.getString("passwords.not.same"), response);
            return "forward:/WEB-INF/errors/inv_pswd.jsp";
        } else if (!"successful.register".equals(userPresentError)){
//            userService.setResponse(400, userPresentError, response);
            return "forward:/WEB-INF/errors/usr_pres.jsp";
        } else {
            userService.registerUser(user);
            log.info("User " + user.getEmail() + " registered");
            userService.authorize(user, request);
            log.info("User " + user.getEmail() + " authorized");
        }

//        System.out.println(user.toString());

        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG);
//        return "forward:/Welcome.jsp";
    }
}
