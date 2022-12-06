module framework {
	exports dev.catalogue.topology.framework;
	requires domain;
	requires application;
	requires org.junit.jupiter.api;
	requires lombok;
	requires jakarta.persistence;
	requires quarkus.hibernate.reactive.panache;
	requires io.smallrye.common.annotation;
	requires org.eclipse.persistence.core;
	requires java.transaction;
	requires java.sql;
	requires jackson.annotations;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires jakarta.inject.api;
	requires jakarta.enterprise.cdi.api;
	requires io.smallrye.mutiny;
	requires quarkus.panache.common;
	requires hibernate.reactive.core;
	requires javax.ws.rs.api;
	requires microprofile.openapi.api;
}