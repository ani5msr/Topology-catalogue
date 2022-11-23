package dev.catalogue.topology.domain.service;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.ID;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class Routerservice {
	public static List<Router> filterAndRetrieveRouter(List<Router> routers, Predicate<Equipment> routerPredicate){
        return routers
                .stream()
                .filter(routerPredicate)
                .collect(Collectors.<Router>toList());
    }
	public static Router findById(Map<ID,Router> routers, ID id){
	        return routers.get(id);
	    }
}
