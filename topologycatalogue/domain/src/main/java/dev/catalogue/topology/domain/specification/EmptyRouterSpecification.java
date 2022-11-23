package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.exception.*;
public class EmptyRouterSpecification extends AbstractSpecification<CoreRouter> {
	 @Override
	    public boolean isSatisfiedBy(CoreRouter coreRouter) {
	        return coreRouter.getRouters()==null||
	                coreRouter.getRouters().isEmpty();
	    }
	 @Override
	    public void check(CoreRouter coreRouter) {
	        if(!isSatisfiedBy(coreRouter))
	            throw new GenericException("It isn't allowed to remove a core router with other routers attached to it");
	    }
}
