package dev.catalogue.topology.framework.adapters.input.rest;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dev.catalogue.topology.application.usecases.RouterUseCase;
import dev.catalogue.topology.domain.valueobj.ID;
import dev.catalogue.topology.domain.valueobj.IP;
import dev.catalogue.topology.framework.adapters.input.rest.request.router.RouterAdd;
import dev.catalogue.topology.framework.adapters.input.rest.request.router.RouterRequest;

@ApplicationScoped
@Path("/router")
@Tag(name = "Router Operations", description = "Router management operations")
public class RouterAdapter {
	@Inject
    RouterUseCase routerUseCase;
   
	@Transactional
    @GET
    @Path("/{id}")
    @Operation(operationId = "retrieveRouter", description = "Retrieve a router from the network catalogue")
    public Uni<Response> retrieveRouter(@PathParam("id") ID id) {
        return Uni.createFrom()
                .item(routerUseCase.retrieveRouter(id))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }
	
	@Transactional
    @DELETE
    @Path("/{id}")
    @Operation(operationId = "removeRouter", description = "Remove a router from the network inventory")
    public Uni<Response> removeRouter(@PathParam("id") ID id) {
        return Uni.createFrom()
                .item(routerUseCase.removeRouter(id))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }
	@Transactional
    @POST
    @Path("/")
    @Operation(operationId = "createRouter", description = "Create and persist a new router on the network inventory")
    public Uni<Response> createRouter(RouterRequest Routerreq) {
        var router = routerUseCase.createRouter(
                null,
                Routerreq.getVendor(),
                Routerreq.getModel(),
                IP.fromAddress(Routerreq.getIp()),
                Routerreq.getLocation(),
                Routerreq.getRouterType()

        );

        return Uni.createFrom()
                .item(routerUseCase.persistRouter(router))
                .onItem()
                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }


}
