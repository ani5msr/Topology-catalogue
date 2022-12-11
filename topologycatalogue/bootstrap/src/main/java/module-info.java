module bootstrap {
	exports dev.catalogue.topology.bootstrap;

	requires org.junit.jupiter.api;
	 requires domain;
	    requires framework;
	    requires application;
	    requires transitive quarkus.core;
}