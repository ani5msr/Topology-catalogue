package dev.catalogue.topology.framework.adapters.input.rest;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dev.catalogue.topology.application.usecases.RouterUseCase;
import dev.catalogue.topology.application.usecases.SwitchUseCase;
import dev.catalogue.topology.domain.entity.EdgeRouter;
import dev.catalogue.topology.domain.entity.Router;
import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.ID;
import dev.catalogue.topology.domain.valueobj.IP;
import dev.catalogue.topology.domain.valueobj.Routertype;
import dev.catalogue.topology.framework.adapters.input.rest.request.SwitchRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/switch")
@Tag(name = "Switch Operations", description = "Operations for switch management")
public class SwitchAdapter {
	    @Inject
	    SwitchUseCase switchUseCase;
	    @Inject
	    RouterUseCase routerUseCase;

	    @Transactional
	    @GET
	    @Path("/{id}")
	    @Operation(operationId = "retrieveSwitch", description = "Retrieve a switch from an edge router")
	    public Uni<Response> retrieveSwitch(@PathParam("id") ID switchId) {
	        return Uni.createFrom()
	                .item(switchUseCase.retrieveSwitch(switchId))
	                .onItem()
	                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
	                .onItem()
	                .transform(Response.ResponseBuilder::build);
	    }
	    @Transactional
	    @POST
	    @Path("/create/{edgeRouterId}")
	    @Operation(operationId = "createAndAddSwitchToEdgeRouter", description = "Create switch and add to an edge router")
	    public Uni<Response> createAndAddSwitchToEdgeRouter(
	            SwitchRequest createSwitch, @PathParam("edgeRouterId") String edgeRouterId
	    ) {
	        Switch newSwitch = switchUseCase.
	                createSwitch(
	                        createSwitch.getVendor(),
	                        createSwitch.getModel(),
	                        IP.fromAddress(createSwitch.getIp()),
	                        createSwitch.getLocation(),
	                        createSwitch.getSwitchType());
	        Router edgeRouter = routerUseCase.retrieveRouter(ID.withId(edgeRouterId));
	        if(!edgeRouter.getRoutertype().equals(Routertype.Edge))
	            throw new UnsupportedOperationException("Please inform the id of an edge router to add a switch");
	        Router router = switchUseCase.addSwitchToEdgeRouter(newSwitch, (EdgeRouter) edgeRouter);

	        return Uni.createFrom()
	                .item((EdgeRouter) routerUseCase.persistRouter(router))
	                .onItem()
	                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
	                .onItem()
	                .transform(Response.ResponseBuilder::build);
	    }

}
