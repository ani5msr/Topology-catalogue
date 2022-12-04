package dev.catalogue.topology.framework.adapters.input.rest;

import dev.catalogue.topology.application.usecases.*;
import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.ID;
import dev.catalogue.topology.domain.valueobj.IP;
import dev.catalogue.topology.domain.valueobj.Network;
import dev.catalogue.topology.framework.adapters.input.rest.request.NetworkRequest;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
@ApplicationScoped
@Path("/network")
@Tag(name = "Network Operations", description = "Network operations")
public class NetworkAdapter {
	 @Inject
	 SwitchUseCase switchUseCase;
	 @Inject
	 NetworkUseCase networkUseCase;
	 @Transactional
	    @POST
	    @Path("/add/{switchId}")
	    @Operation(operationId = "addNetworkToSwitch", description = "Add network to a switch")
	    public Uni<Response> addNetworkToSwitch(NetworkRequest NetworkReq, @PathParam("switchId") String switchId) {
	        Switch networkSwitch = switchUseCase.retrieveSwitch(ID.withId(switchId));

	        Network network = Network.builder()
	                .networkAddress(IP.fromAddress(NetworkReq.getNetworkAddress()))
	                .networkName(NetworkReq.getNetworkName())
	                .networkCidr(NetworkReq.getNetworkCidr())
	                .build();

	        return Uni.createFrom()
	                .item(networkUseCase.addNetworkToSwitch(network, networkSwitch))
	                .onItem()
	                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
	                .onItem()
	                .transform(Response.ResponseBuilder::build);
	    }
	 @Transactional
	    @DELETE
	    @Path("/{networkName}/from/{switchId}")
	    @Operation(operationId = "removeNetworkFromSwitch", description = "Remove network from a switch")
	    public Uni<Response> removeNetworkFromSwitch(@PathParam("networkName") String networkName, @PathParam("switchId") String switchId) {
	        Switch networkSwitch = switchUseCase.retrieveSwitch(ID.withId(switchId));

	        return Uni.createFrom()
	                .item(networkUseCase.removeNetworkFromSwitch(networkName, networkSwitch))
	                .onItem()
	                .transform(f -> f != null ? Response.ok(f) : Response.ok(null))
	                .onItem()
	                .transform(Response.ResponseBuilder::build);
	    }
}
