package dev.catalogue.topology.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.catalogue.topology.domain.valueobj.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.specification.common.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class MainTest {
	 private Location createLocation(String country){
	        return Location.builder().
	                address("Test street").
	                city("Test City").
	                state("Test State").
	                country(country).
	                zipCode(00000).
	                latitude(10F).
	                longitude(-10F).
	                build();
	    }
	 private List<Network> createNetworks(Network network){
	        List<Network> networks = new ArrayList<>();
	        networks.add(network);
	        return networks;
	    }
	 private Switch createNetworkSwitch(Location location, List<Network> networks){
	        return Switch.builder().
	                id(ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0004).
	                ip(IP.fromAddress("20.0.0.100")).
	                location(location).
	                switchType(Switchtype.Layer3).
	                switchNetworks(networks).
	                build();
	    }
	  private Network createTestNetwork(String address, int CIDR){
	        return Network.builder().
	                networkAddress(IP.fromAddress(address)).
	                networkName("NewNetwork").
	                networkCidr(CIDR).
	                build();
	    }
	  private Switch createSwitch(String address, int cidr, Location location){
	        var newNetwork = createTestNetwork(address, cidr);
	        var networks = createNetworks(newNetwork);
	        var networkSwitch = createNetworkSwitch(location, networks);
	        return networkSwitch;
	    }
}
