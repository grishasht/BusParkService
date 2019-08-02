package web.filters;

import model.util.LogGenerator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private Logger log = LogGenerator.getInstance();
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
        log.info("Encoding filter initted");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, NullPointerException{
        if (servletRequest.getCharacterEncoding() != null) {
            servletRequest.setCharacterEncoding(encoding);
        }
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        log.info("Encoding was set");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
