package superbad.idea.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // nothing here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("Requête entrante depuis {}, {}", request.getRemoteAddr(), request.getRemoteHost());
        HttpServletRequest req = (HttpServletRequest) request;
        Set<Entry<String, String>> entrySet = req.getParameterMap().entrySet();
        for (Entry<String, String> entry : entrySet){
            LOG.info("Parametres : {}/{}", entry.getKey(), entry.getValue());
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // nothing here
    }

}
