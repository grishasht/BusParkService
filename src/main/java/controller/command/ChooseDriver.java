package controller.command;

import model.entity.Bus;
import model.entity.Direction;
import model.entity.User;
import model.service.BusService;
import model.service.UserService;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChooseDriver implements Command {
    private UserService userService = new UserService();
    private BusService busService = new BusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int driver_id = Integer.parseInt(request.getParameter(Constants.DRIVER_ID));
            int bus_id = Integer.parseInt(request.getParameter(Constants.BUS_ID));
            User user = userService.readById(driver_id);
            userService.setFree(false, user);
            Bus bus = busService.readById(bus_id);
            busService.setFree(false, bus, driver_id);
        } catch (Exception e){
            return "forward:/WEB-INF/errors/not_found.jsp";
        }


        return "redirect:" + request.getContextPath() + "/page/manage_buses?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG)
                + "&count=" + request.getSession().getAttribute(Constants.PAGE_ID);
    }
}
