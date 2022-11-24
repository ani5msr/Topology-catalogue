package dev.catalogue.topology.domain.entity;

import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.catalogue.topology.domain.valueobj.*;
@Getter
public class Equipment {
	@Getter
	protected ID id;
	@Getter
	protected Vendor vendor;
	@Getter
	protected Model model;
	@Getter
	protected IP ip;
	@Getter
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
	public Location getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}
	public IP getIp() {
		// TODO Auto-generated method stub
		return this.ip;
	}

}
