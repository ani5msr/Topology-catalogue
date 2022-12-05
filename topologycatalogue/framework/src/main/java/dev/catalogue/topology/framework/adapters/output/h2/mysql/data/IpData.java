package dev.catalogue.topology.framework.adapters.output.h2.mysql.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class IpData {
	 private String address;
	 
	 @Enumerated(EnumType.STRING)
	 @Embedded
	 private ProtocolData protocol;
	 private IpData(String address){
	        if(address == null)
	            throw new IllegalArgumentException("Null IP address");
	        this.address = address;
	        if(address.length()<=15) {
	            this.protocol = ProtocolData.IPV4;
	        } else {
	            this.protocol = ProtocolData.IPV6;
	        }
	    }

	 public IpData() {

	    }

	 public static IpData fromAddress(String address){
	        return new IpData(address);
	    }
}
