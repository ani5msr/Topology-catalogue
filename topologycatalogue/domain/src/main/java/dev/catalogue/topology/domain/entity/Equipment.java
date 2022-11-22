package dev.catalogue.topology.domain.entity;

import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.catalogue.topology.domain.valueobj.*;
@Getter

public class Equipment {
	protected ID id;
	protected Vendor vendor;
	protected Model model;
	protected IP ip;
	protected Location location;
	public static Predicate<Equipment>
	 getVendorPredicate(Vendor vendor){
	 return r -> r.getVendor().equals(vendor);
	 }
	protected Vendor getVendor() {
		return this.vendor;
	}

}
