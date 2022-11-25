package dev.catalogue.topology.domain.entity.factory;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
public class Routerfactory {
	public static Router getRouter(ID id,Vendor vendor,Model model,IP ip,Location location,Routertype routerType){
		switch (routerType){
        case Core:
            return CoreRouter.builder().
                    id(id==null ? ID.withoutId():id).
                    vendor(vendor).
                    model(model).
                    ip(ip).
                    location(location).
                    routerType(routerType).
                    build();
        case Edge:
            return EdgeRouter.builder().
                    id(id==null ? ID.withoutId():id).
                    vendor(vendor).
                    model(model).
                    ip(ip).
                    location(location).
                    routerType(routerType).
                    build();
        default:
            return null;
    }
	}


}
