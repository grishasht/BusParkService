package controller.command;

import model.entity.Bus;
import model.entity.Direction;
import model.entity.Request;
import model.entity.User;
import model.service.BusService;
import model.service.RequestService;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateReq implements Command {
    private RequestService requestService = new RequestService();
    private BusService busService = new BusService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constants.DRIVER);
        Bus bus = (Bus) request.getSession().getAttribute(Constants.BUS);
        Direction direction = (Direction) request.getSession().getAttribute(Constants.DIRECTION);

        Request request1 = new Request();
        request1.setBusId(bus.getId());
        request1.setDirectionId(direction.getId());
        request1.setUserId(user.getId());
        request1.setIsAccept(false);


        try {
            requestService.insertRequest(request1);
            busService.setFree(false, bus, bus.getUserId());
            request.getSession().setAttribute(Constants.CREATE_REQ, false);
        } catch (Exception e){
            return "forward:/WEB-INF/errors/smth_wrong.jsp";
        }
        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG)
                + "&count=" + request.getSession().getAttribute(Constants.PAGE_ID);
    }
}
