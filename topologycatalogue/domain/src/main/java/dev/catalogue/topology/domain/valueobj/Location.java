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
	@Getter
	private String address;
	 @Getter
	 private String city;
	 @Getter
	 private String state;
	 @Getter
	 private int zipCode;
	 @Getter
	 private String country;
	 @Getter
	 private float latitude;
	 @Getter
	 private float longitude;
	public String getCountry() {
		return this.country;
	}
}
