package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.exception.*;
public class EmptySwitchSpecification extends AbstractSpecification<EdgeRouter>{
	@Override
    public boolean isSatisfiedBy(EdgeRouter edgeRouter) {
        return edgeRouter.getSwitches()==null ||
                edgeRouter.getSwitches().isEmpty();
    }

    @Override
    public void check(EdgeRouter edgeRouter) {
        if(!isSatisfiedBy(edgeRouter))
            throw new GenericException("It isn't allowed to remove an edge router with a switch attached to it");
    }
}
