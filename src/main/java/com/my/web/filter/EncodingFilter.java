package com.my.web.filter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 *
 * @author
 */
public class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    public void destroy() {
        BasicConfigurator.configure();
        LOG.debug("Filter destruction starts");
        // no op
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        BasicConfigurator.configure();
        LOG.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {

            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
        LOG.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encoding);
        LOG.debug("Filter initialization finished");
    }

}