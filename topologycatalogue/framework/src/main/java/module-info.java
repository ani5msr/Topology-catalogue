module framework {
	exports dev.catalogue.topology.framework;
	requires domain;
	requires application;
	requires org.junit.jupiter.api;
	requires lombok;
	requires jakarta.persistence;
	requires org.eclipse.persistence.core;
}