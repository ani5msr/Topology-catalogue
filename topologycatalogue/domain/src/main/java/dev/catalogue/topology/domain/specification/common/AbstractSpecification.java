package dev.catalogue.topology.domain.specification.common;
import dev.catalogue.topology.domain.exception.*;
public abstract class AbstractSpecification<T> implements Specification<T>{
		public abstract boolean isSatisfiedBy(T t);
		public Specification<T> and(final Specification<T> specification) {
	        return new AndSpecification<T>(this, specification);
	    }
		public abstract void check(T t) throws GenericException;
}
