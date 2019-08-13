package controller.command;

import model.service.RequestService;
import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptReq implements Command {
    private RequestService requestService = new RequestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer reqId = Integer.parseInt(request.getParameter(Constants.REQ_ID));
            requestService.setAccepted(reqId);
            request.getSession().setAttribute(Constants.REQ_ACCEPTED, true);
        } catch (Exception e){
            return "forward:/WEB-INF/errors/smth_wrong.jsp";
        }

        return "redirect:" + request.getContextPath() + "/page/?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG);
    }
}
