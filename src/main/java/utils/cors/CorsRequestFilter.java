package utils.cors;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(CorsRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {
            log.info("HTTP Method (OPTIONS) - Detected!");
            requestCtx.abortWith(Response.status(Response.Status.OK).build());
        }
    }
}
