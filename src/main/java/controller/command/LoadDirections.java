package controller.command;

import model.service.DirectionService;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadDirections implements Command {
    private DirectionService directionService = new DirectionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute(Constants.PAGE_ID,
                    Integer.valueOf(request.getParameter(Constants.PAGE_ID)));
        } catch (Exception e){
            return "forward:/WEB-INF/errors/not_found.jsp";
        }

        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG)
                + "&count=" + request.getSession().getAttribute(Constants.PAGE_ID);
    }
}
