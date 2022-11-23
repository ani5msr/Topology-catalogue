package dev.catalogue.topology.domain.entity;

import dev.catalogue.topology.domain.specification.CIDRSpecification;
import dev.catalogue.topology.domain.specification.NetworkAmountSpecification;
import dev.catalogue.topology.domain.specification.NetworkAvailabilitySpecification;
import dev.catalogue.topology.domain.valueobj.*;
import lombok.Builder;

import java.util.List;
import java.util.function.Predicate;
public class Switch extends Equipment {
	private Switchtype switchType;
	private List<Network> switchNetworks;
	public Switchtype getSwitchType() {
		return switchType;
	}
	public void setSwitchType(Switchtype switchType) {
		this.switchType = switchType;
	}
	public List<Network> getSwitchNetworks() {
		return switchNetworks;
	}
	public void setSwitchNetworks(List<Network> switchNetworks) {
		this.switchNetworks = switchNetworks;
	}
	@Builder
    public Switch(ID id, Vendor vendor, Model model, IP ip, Location location, Switchtype switchType, List<Network> switchNetworks){
        super(id, vendor, model, ip, location);
        this.switchType = switchType;
        this.switchNetworks = switchNetworks;
    }
	 public static Predicate<Network> getNetworkProtocolPredicate(Protocol protocol){
	        return s -> s.getNetworkAddress().getProtocol().equals(protocol);
	    }
	 public static Predicate<Switch> getSwitchTypePredicate(Switchtype switchType){
	        return s -> s.switchType .equals(switchType);
	    }
	 public boolean addNetworkToSwitch(Network network) {
	        var availabilitySpec = new NetworkAvailabilitySpecification(network);
	        var cidrSpec = new CIDRSpecification();
	        var amountSpec = new NetworkAmountSpecification();

	        cidrSpec.check(network.getNetworkCidr());
	        availabilitySpec.check(this);
	        amountSpec.check(this);

	        return this.switchNetworks.add(network);
	    }
	 public boolean removeNetworkFromSwitch(Network network){
	        return this.switchNetworks.remove(network);
	    }
}
