package dev.catalogue.topology.domain.specification.common;

public interface Specification<T> {
	boolean isSatisfiedBy(T t);
    Specification<T> and(Specification<T> specification);
}
