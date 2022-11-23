package dev.catalogue.topology.domain.entity;

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
	 public boolean removeNetworkFromSwitch(Network network){
	        return this.switchNetworks.remove(network);
	    }
}
