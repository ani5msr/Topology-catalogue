package dev.catalogue.topology.framework.adapters.input.rest;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dev.catalogue.topology.application.usecases.RouterUseCase;
import dev.catalogue.topology.application.usecases.SwitchUseCase;
import dev.catalogue.topology.domain.valueobj.ID;

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
}
