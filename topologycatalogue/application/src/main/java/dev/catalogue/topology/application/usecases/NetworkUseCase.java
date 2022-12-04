package dev.catalogue.topology.application.usecases;

import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
public interface NetworkUseCase {
	Network createNetwork(IP networkAddress, String networkName, int networkCidr);
	Switch addNetworkToSwitch(Network network, Switch networkSwitch);
	Switch removeNetworkFromSwitch(String name, Switch networkSwitch);
}
