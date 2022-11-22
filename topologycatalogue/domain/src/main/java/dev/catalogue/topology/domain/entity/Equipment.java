package dev.catalogue.topology.domain.entity;

import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.catalogue.topology.domain.valueobj.*;
@Getter
@AllArgsConstructor
public class Equipment {
	protected ID id;
	protected Vendor vendor;
	protected Model model;
	protected IP ip;
	protected Location location;
	public Equipment(ID id, Vendor vendor, Model model, IP ip, Location location) {
		this.id = id;
		this.vendor = vendor;
		this.model = model;
		this.ip = ip;
		this.location = location;
	}
	public static Predicate<Equipment>
	 getVendorPredicate(Vendor vendor){
	 return r -> r.getVendor().equals(vendor);
	 }
	protected Vendor getVendor() {
		return this.vendor;
	}
	public Model getModel() {
		return this.model;
	}

}
