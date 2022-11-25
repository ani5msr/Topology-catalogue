package dev.catalogue.topology.application.ports.input;
import dev.catalogue.topology.application.usecases.*;

import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NetworkInputPort implements NetworkUseCase{
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
        networkSwitch.addNetworkToSwitch(network);
        return networkSwitch;
    }
	@Override
    public Switch removeNetworkFromSwitch(Network network, Switch networkSwitch) {
        networkSwitch.removeNetworkFromSwitch(network);
        return networkSwitch;
    }
}
