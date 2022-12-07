package dev.catalogue.topology.application.java.mocks;

import io.quarkus.test.Mock;
import dev.catalogue.topology.application.ports.output.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
@Mock
public class RouterOutputPortmock implements RouterOutputPort{
	@Override
    public Router retrieveRouter(ID id) {
        return null;
    }

    @Override
    public Router removeRouter(ID id) {
        return null;
    }

    @Override
    public Router persistRouter(Router router) {
        return null;
    }
}
