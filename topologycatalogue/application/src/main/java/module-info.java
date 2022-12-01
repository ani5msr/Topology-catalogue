module application {
	exports dev.catalogue.topology.application;
	exports dev.catalogue.topology.application.ports.output;
	exports dev.catalogue.topology.application.ports.input;
	exports dev.catalogue.topology.application.usecases;
    requires transitive domain;
    requires static lombok;
	requires org.junit.jupiter.api;
}