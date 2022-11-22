package dev.catalogue.topology.domain.entity;

import dev.catalogue.topology.domain.valueobj.*;
import lombok.Getter;
import java.util.function.Predicate;
@Getter
public abstract class Router extends Equipment{
	protected final Routertype routertype;
	public static Predicate<Equipment> getRouterTypePredicate(Routertype routerType){
	        return r -> ((Router)r).getRouterType().equals(routerType);
	    }
	public static Predicate<Equipment> getModelPredicate(Model model){
	        return r -> r.getModel().equals(model);
	    }
    public static Predicate<Equipment> getCountryPredicate(Location location){
	        return p -> p.location.getCountry().equals(location.getCountry());
	    }
	private Routertype getRouterType() {
		return this.routertype;
	}
	public Router(ID id, Vendor vendor, Model model, IP ip, Location location, Routertype routerType) {
        super(id, vendor, model, ip, location);
        this.routertype = routerType;
    }

}
