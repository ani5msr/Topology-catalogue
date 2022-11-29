package dev.catalogue.topology.domain.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.catalogue.topology.domain.valueobj.Network;

public class Networkservice {
	 public static List<Network> filterAndRetrieveNetworks(List<Network> networks, Predicate<Network> networkPredicate){
	        return networks
	                .stream()
	                .filter(networkPredicate)
	                .collect(Collectors.<Network>toList());
	    }
	 public static Network findNetwork(List<Network> networks, Predicate<Network> networkPredicate){
	        return networks
	                .stream()
	                .filter(networkPredicate)
	                .findFirst().orElse(null);
	    }
}
