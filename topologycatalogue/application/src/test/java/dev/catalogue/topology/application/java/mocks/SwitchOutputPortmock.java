package dev.catalogue.topology.application.java.mocks;
import io.quarkus.test.Mock;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.application.ports.output.*;

@Mock
public class SwitchOutputPortmock implements SwitchOutputPort{
	    @Override
	    public Switch retrieveSwitch(ID id) {
	        return null;
	    }
}
