module application {
	exports dev.catalogue.topology.application;
	exports dev.catalogue.topology.application.ports.output;
	exports dev.catalogue.topology.application.ports.input;
	exports dev.catalogue.topology.application.usecases;
    requires transitive domain;
    requires static lombok;
	requires org.junit.jupiter.api;
	requires javaee.web.api;
	
	provides dev.catalogue.topology.application.usecases.RouterUseCase with 
	dev.catalogue.topology.application.ports.input.RouterInputPort;
	provides dev.catalogue.topology.application.usecases.SwitchUseCase with
	dev.catalogue.topology.application.ports.input.SwitchInputPort;
	provides dev.catalogue.topology.application.usecases.NetworkUseCase with
	dev.catalogue.topology.application.ports.input.NetworkInputPort;
}