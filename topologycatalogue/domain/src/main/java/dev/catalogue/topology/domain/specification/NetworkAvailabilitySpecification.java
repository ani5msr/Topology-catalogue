package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.exception.*;
import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
public class NetworkAvailabilitySpecification extends AbstractSpecification<Equipment> {
	private IP address;
    private String name;
    private int cidr;

    public NetworkAvailabilitySpecification(Network network){
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }
    
    @Override
    public boolean isSatisfiedBy(Equipment switchnetworks) {
    	return switchnetworks!=null && isNetworkAvailable(switchnetworks);
    }
    
    @Override
    public void check(Equipment equipment) throws GenericException {
    	if(!isSatisfiedBy(equipment)) {
    		throw new GenericException("This network already exists");
    	}
    }
    
    private boolean isNetworkAvailable(Equipment switchnetworks){
        var availability = true;
        for (Network network : ((Switch)switchnetworks).getSwitchNetworks()) {
            if(network.getNetworkAddress().equals(address) &&
                    network.getNetworkName().equals(name) &&
                    network.getNetworkCidr() == cidr)
                availability = false;
            break;
        }
        return availability;
    }

}
