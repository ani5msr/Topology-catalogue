module application {
	exports dev.catalogue.topology.application;
	exports dev.catalogue.topology.application.ports.output;
    requires domain;
    requires static lombok;
	requires org.junit.jupiter.api;
}