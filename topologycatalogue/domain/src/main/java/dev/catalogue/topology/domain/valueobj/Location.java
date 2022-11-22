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
public class Location {
	private String address;
	 private String city;
	 private String state;
	 private int zipCode;
	 private String country;
	 private float latitude;
	 private float longitude;
	public String getCountry() {
		// TODO Auto-generated method stub
		return this.country;
	}
}
