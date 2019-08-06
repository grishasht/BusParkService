package model.service;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entity.User;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserService {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~\\-]{1,20}@[a-zA-Z0-9-]{1,10}(?:\\.[a-zA-Z0-9-]+){1,5}$";
    private static final String PSWD_REGEX = "[^\\s]{6,20}";
    private static final String USERNAME_REGEX = "[^\\^%$'\"#@!?\\-+=\\s]{1,20}";


    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static Dao<User> userDao = daoFactory.createUserDao();

    public void registerUser(User user) {
        userDao.create(user);
    }

    public User identifyUser(String login, String password) {
        List<User> users = userDao.readAll();
        Optional<User> optionalUser = users.stream()
                .filter(user1 -> user1.getLogin().equals(login) && user1.getPassword().equals(password))
                .findAny();

        return optionalUser.orElse(User.getGuest());
    }

    public String userPresentError(User user, ResourceBundle resourceBundle) {
        List<User> users = userDao.readAll();
        if (verifyLogin(user.getLogin(), users)) {
            return resourceBundle.getString("fail.username.exist");
        } else if (verifyEmail(user.getEmail(), users)) {
            return resourceBundle.getString("fail.email.exist");
        } else
            return "successful.register";
    }

    public String validationInputErr(User user, ResourceBundle resourceBundle){
        String error = "";

        if (!validateEmail(user.getEmail())){
            error += resourceBundle.getString("email.validation.fail") + "\n";
        }

        if (!validateUsername(user.getName())){
            error += resourceBundle.getString("username.validation.fail") + "\n";
        }

        if (!validatePassword(user.getPassword())){
            error += resourceBundle.getString("password.validation.fail") + "\n";
        }

        return error;
    }

    public Boolean confirmPassword(String pswd1, String pswd2) {
        return pswd1.equals(pswd2);
    }

    public void authorize(User user, HttpServletRequest request){
        request.getSession().setAttribute(Constants.SESSION_USER, user);
    }

    public void setResponse(int status, String message, HttpServletResponse response){
        response.setStatus(status);
        try {
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean verifyLogin(String login, List<User> users) {
        return users.stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    private Boolean verifyEmail(String email, List<User> users) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    private Boolean validateEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    private Boolean validateUsername(String username){
        return username.matches(USERNAME_REGEX);
    }

    private Boolean validatePassword(String password){
        return password.matches(PSWD_REGEX);
    }

    public static Integer getUserId(String login) {
        List<User> users = userDao.readAll();
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny()
                .get()
                .getId();
    }

}
