package dev.catalogue.topology.application.ports.input;
import java.util.function.Predicate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dev.catalogue.topology.application.ports.output.RouterOutputPort;
import dev.catalogue.topology.application.usecases.*;

import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.service.Networkservice;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor
public class NetworkInputPort implements NetworkUseCase{
	@Inject
    RouterOutputPort routerOutputPort;
	@Override
    public Network createNetwork(
            IP networkAddress, String networkName, int networkCidr) {
        return Network
                .builder()
                .networkAddress(networkAddress)
                .networkName(networkName)
                .networkCidr(networkCidr)
                .build();
    }	
	@Override
    public Switch addNetworkToSwitch(Network network, Switch networkSwitch) {
        ID routerId = networkSwitch.getRouterId();
        ID switchId = networkSwitch.getId();
        EdgeRouter edgeRouter = (EdgeRouter) routerOutputPort
                .retrieveRouter(routerId);
        Switch switchToAddNetwork = edgeRouter
                .getSwitches()
                .get(switchId);
        switchToAddNetwork.addNetworkToSwitch(network);
        routerOutputPort.persistRouter(edgeRouter);
        return switchToAddNetwork;
    }
	 @Override
	    public Switch removeNetworkFromSwitch(String networkName, Switch networkSwitch) {
	        ID routerId = networkSwitch.getRouterId();
	        ID switchId = networkSwitch.getId();
	        EdgeRouter edgeRouter = (EdgeRouter) routerOutputPort
	                .retrieveRouter(routerId);
	        Switch switchToRemoveNetwork = edgeRouter
	                .getSwitches()
	                .get(switchId);
	        Predicate<Network> networkPredicate = Network.getNetworkNamePredicate(networkName);
	        var network = Networkservice.
	                findNetwork(switchToRemoveNetwork.getSwitchNetworks(), networkPredicate);
	        routerOutputPort.persistRouter(edgeRouter);
	        return switchToRemoveNetwork.removeNetworkFromSwitch(network)
	                ? switchToRemoveNetwork
	                : null;
	    }
}
