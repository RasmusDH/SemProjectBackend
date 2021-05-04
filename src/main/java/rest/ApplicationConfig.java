package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(utils.cors.CorsRequestFilter.class);
        resources.add(utils.cors.CorsResponseFilter.class);
        resources.add(utils.errorhandling.API_ExceptionMapper.class);
        resources.add(utils.errorhandling.GenericExceptionMapper.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
        resources.add(rest.DemoResource.class);
        resources.add(rest.RenameMeResource.class);
        resources.add(security.JWTAuthenticationFilter.class);
        resources.add(security.LoginEndpoint.class);
        resources.add(security.RolesAllowedFilter.class);
        resources.add(security.errorhandling.AuthenticationExceptionMapper.class);
        resources.add(security.errorhandling.NotAuthorizedExceptionMapper.class);
    }
    
}
