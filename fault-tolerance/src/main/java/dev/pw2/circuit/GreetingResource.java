package dev.pw2.circuit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Fallback;

@Path("/hello")
public class GreetingResource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 3, delay = 2000)
    @Fallback(fallbackMethod = "recover")
    public int getId(@PathParam("id") int id) {
        Coffee coffee = new Coffee();
        coffee.setId(id);
        return coffee.getId();
    }
    
    // Método que irá ser executado caso o método getName não se recupere da falha
    public int recover(int id) {
        return id;
    }
    
}
