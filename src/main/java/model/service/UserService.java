package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserService {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~\\-]{1,20}@[a-zA-Z0-9-]{1,10}(?:\\.[a-zA-Z0-9-]+){1,5}$";
    private static final String PSWD_REGEX = "[^\\s]{6,20}";
    private static final String USERNAME_REGEX = "[^\\^%$'\"#@!?\\-+=\\s]{1,20}";


    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static UserDao userDao = daoFactory.createUserDao();

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
            return error;
        }

        if (!validateUsername(user.getName())){
            error += resourceBundle.getString("username.validation.fail") + "\n";
            return error;
        }

        if (!validatePassword(user.getPassword())){
            error += resourceBundle.getString("password.validation.fail") + "\n";
            return error;
        }

        if (!validateAge(user.getAge())){
            error += resourceBundle.getString("age.validation.fail");
            return error;
        }

        return error;
    }

    private boolean validateAge(Integer age) {
        return age > 0;
    }

    public Boolean confirmPassword(String pswd1, String pswd2) {
        return pswd1.equals(pswd2);
    }

    public void authorize(User user, HttpServletRequest request){
        request.getSession().setAttribute(Constants.SESSION_USER, user);
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
                .orElse(User.getGuest())
                .getId();
    }

    public List<User> getDrivers(){
        return userDao.getDrivers();
    }

    public User readById(int id){
        return userDao.readById(id);
    }

    public void setFree(Boolean isFree, User entity){
        User user = new User();
        user.setId(entity.getId());
        user.setAge(entity.getAge());
        user.setFree(isFree);
        user.setName(entity.getName());

        userDao.update(user);
    }

}
