package dev.catalogue.topology.domain.valueobj;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class IP {
	 private String ipAddress;
	 private Protocol protocol;
     public IP(String ipAddress){
	      if(ipAddress == null)
	          throw new IllegalArgumentException("Null IP address");
	      this.ipAddress = ipAddress;
	      if(ipAddress.length()<=15) {
	          this.protocol = Protocol.IPV4;
	      } else {
	        this.protocol = Protocol.IPV6;
	      }
	    }
	 public static IP fromAddress(String ipAddress){
	        return new IP(ipAddress);
	    }
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @return the protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}
}
