package controller.command;

import model.entity.Bus;
import model.entity.Direction;
import model.entity.User;
import model.service.BusService;
import model.service.DirectionService;
import model.service.UserService;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChooseBus implements Command {
    private DirectionService directionService = new DirectionService();
    private BusService busService = new BusService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int bus_id = Integer.parseInt(request.getParameter(Constants.BUS_ID));
            int direction_id = Integer.parseInt(request.getParameter(Constants.DIRECTION_ID));

            Direction direction = directionService.readById(direction_id);
            direction.setBusId(bus_id);
            Bus bus = busService.readById(bus_id);
            User user = userService.readById(bus.getUserId());
            directionService.setFree(false, direction);
            request.getSession().setAttribute(Constants.CREATE_REQ, true);
            request.getSession().setAttribute(Constants.DIRECTION, direction);
            request.getSession().setAttribute(Constants.BUS, bus);
            request.getSession().setAttribute(Constants.DRIVER, user);

        } catch (Exception e){
            return "forward:/WEB-INF/errors/smth_wrong.jsp";
        }


        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG)
                + "&count=" + request.getSession().getAttribute(Constants.PAGE_ID);
    }
}
