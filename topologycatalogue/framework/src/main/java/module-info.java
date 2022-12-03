module framework {
	exports dev.catalogue.topology.framework;
	requires domain;
	requires application;
	requires org.junit.jupiter.api;
	requires lombok;
	requires jakarta.persistence;
	requires quarkus.hibernate.reactive.panache;
	requires smallrye.common.annotation;
	requires org.eclipse.persistence.core;
	requires java.transaction;
	requires java.sql;
}