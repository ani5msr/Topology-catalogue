package dev.catalogue.topology.domain.valueobj;
import java.util.function.Predicate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Network {
	private IP networkAddress;
    private String networkName;
	private int networkCidr;
    public static Predicate<Network> getNetworkProtocolPredicate(Protocol protocol){
	        return s -> s.getNetworkAddress().getProtocol().equals(protocol);
	    }

	public static Predicate<Network> getNetworkNamePredicate(String name){
	        return s -> s.getNetworkName().equals(name);
	    }
	public Network(IP networkAddress,String networkName, int networkCidr){
			 if(networkCidr <1 || networkCidr>32){
			 throw new IllegalArgumentException(
			 "Invalid CIDR value");
			 }
			 this.networkAddress = networkAddress;
			 this.networkName = networkName;
			 this.networkCidr = networkCidr;
			 }
	/**
	 * @return the networkAddress
	 */
	public IP getNetworkAddress() {
		return networkAddress;
	}
	/**
	 * @return the networkName
	 */
	public String getNetworkName() {
		return networkName;
	}
	/**
	 * @return the networkCidr
	 */
	public int getNetworkCidr() {
		return networkCidr;
	}
}
