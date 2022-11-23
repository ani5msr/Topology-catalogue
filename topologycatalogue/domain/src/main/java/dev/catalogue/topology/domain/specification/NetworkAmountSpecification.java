package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.exception.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.specification.common.*;
public class NetworkAmountSpecification extends AbstractSpecification<Equipment> {
	final static public int MAXIMUM_NETWORKS = 10;
	public boolean isSatisfiedBy(Equipment switchNetwork) {
		 return ((Switch)switchNetwork).getSwitchNetworks().size()
	                <=MAXIMUM_NETWORKS;
	}
	public void check(Equipment equipment) {
		if(!isSatisfiedBy(equipment)) {
			 throw new GenericException("The max number of networks is "+ NetworkAmountSpecification.MAXIMUM_NETWORKS);
		}
	}
}
