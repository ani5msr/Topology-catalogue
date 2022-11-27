module domain {
	exports dev.catalogue.topology.domain.valueobj;
	exports dev.catalogue.topology.domain.entity.factory;
	exports dev.catalogue.topology.domain.service;
	exports dev.catalogue.topology.domain;
	exports dev.catalogue.topology.domain.specification;
	exports dev.catalogue.topology.domain.exception;
	exports dev.catalogue.topology.domain.specification.common;
	exports dev.catalogue.topology.domain.entity;

	requires lombok;
	requires org.junit.jupiter.api;
}