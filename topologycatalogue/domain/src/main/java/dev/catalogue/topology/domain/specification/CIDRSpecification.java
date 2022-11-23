package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.exception.*;
public class CIDRSpecification extends AbstractSpecification<Integer> {
	 final static public int MINIMUM_CIDR = 8;
	 @Override
	 public boolean isSatisfiedBy(Integer cidr) {
	        return cidr >= MINIMUM_CIDR;
	 }
	 @Override
	 public void check(Integer cidr) throws GenericException {
		 if(!isSatisfiedBy(cidr)) {
			 throw new GenericException("CIDR is below "+CIDRSpecification.MINIMUM_CIDR);
		 }
	 }

}
