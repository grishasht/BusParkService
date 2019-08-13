package web.filters;

import model.util.Constants;
import model.util.LogGenerator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {
    private Logger log = LogGenerator.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Locale.setDefault(Locale.ENGLISH);
        log.info("Language filter initted");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getParameter(Constants.CUR_LANG) != null){
            request.getSession().setAttribute(Constants.CUR_LANG, request.getParameter(Constants.CUR_LANG));
        } else {
            request.getSession().setAttribute(Constants.CUR_LANG, Locale.getDefault().getLanguage());
        }
        log.info("User language was set in language filter");

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
