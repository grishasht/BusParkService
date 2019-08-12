package controller.command;

import model.entity.Bus;
import model.entity.Direction;
import model.entity.User;
import model.service.BusService;
import model.service.DirectionService;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChooseBus implements Command {
    private DirectionService directionService = new DirectionService();
    private BusService busService = new BusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int bus_id = Integer.parseInt(request.getParameter(Constants.BUS_ID));
            int direction_id = Integer.parseInt(request.getParameter(Constants.DIRECTION_ID));

            Bus bus = busService.readById(bus_id);
            Direction direction = directionService.readById(direction_id);
            directionService.setFree(false, direction);

        } catch (Exception e){
            return "forward:/WEB-INF/errors/not_found.jsp";
        }


        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG)
                + "&count=" + request.getSession().getAttribute(Constants.PAGE_ID);
    }
}
