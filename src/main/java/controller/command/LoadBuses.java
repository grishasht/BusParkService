package controller.command;

import model.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadBuses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute(Constants.PAGE_ID,
                    Integer.valueOf(request.getParameter(Constants.PAGE_ID)));
        } catch (Exception e){
            return "forward:/WEB-INF/errors/not_found.jsp";
        }

        return "redirect:" + request.getContextPath() + "/page/manage_buses?curLang="
                + request.getSession().getAttribute(Constants.CUR_LANG)
                + "&count=" + request.getSession().getAttribute(Constants.PAGE_ID);
    }
}
