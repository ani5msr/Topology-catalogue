package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.exception.GenericException;
public class EmptyNetworkSpecification extends AbstractSpecification<Switch> {
	
	@Override
	public boolean isSatisfiedBy(Switch switchnetwork) {
		return switchnetwork.getSwitchNetworks()==null||
                switchnetwork.getSwitchNetworks().isEmpty();
	}
	@Override
	public void check(Switch switchnet) {
		if(!isSatisfiedBy(switchnet)) {
			throw new GenericException("It's not possible to remove a switch with networks attached to it");
		}
	}
}
