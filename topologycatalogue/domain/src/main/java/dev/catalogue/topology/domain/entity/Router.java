package dev.catalogue.topology.domain.entity;

import dev.catalogue.topology.domain.valueobj.*;
import lombok.Getter;
import java.util.function.Predicate;
@Getter
public abstract class Router extends Equipment{
	@Getter
	protected final Routertype routertype;
	public static Predicate<Equipment> getRouterTypePredicate(Routertype routertype){
	        return r -> ((Router)r).getRoutertype().equals(routertype);
	    }
	public static Predicate<Equipment> getModelPredicate(Model model){
	        return r -> r.getModel().equals(model);
	    }
    public static Predicate<Equipment> getCountryPredicate(Location location){
	        return p -> p.location.getCountry().equals(location.getCountry());
	    }
	public Router(ID id, Vendor vendor, Model model, IP ip, Location location, Routertype routerType) {
        super(id, vendor, model, ip, location);
        this.routertype = routerType;
    }

}
