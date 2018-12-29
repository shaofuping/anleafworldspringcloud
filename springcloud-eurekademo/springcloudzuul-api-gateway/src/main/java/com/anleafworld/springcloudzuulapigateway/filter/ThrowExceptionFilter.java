package com.anleafworld.springcloudzuulapigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ThrowExceptionFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("This is a pre filter will throw a runtime exception");
        myException();
        RequestContext rc = RequestContext.getCurrentContext();
       /* try {
            myException();
        } catch (Exception e) {
            rc.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            rc.set("error.exception", e);
        }
*/
        return null;
    }

    private void myException() {
        throw new RuntimeException("test zuul error filter.......");
    }
}
