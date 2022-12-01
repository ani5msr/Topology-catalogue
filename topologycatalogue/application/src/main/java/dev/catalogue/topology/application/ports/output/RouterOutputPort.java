package dev.catalogue.topology.application.ports.output;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
public interface RouterOutputPort {
	 Router retrieveRouter(ID id);
	 Router persistRouter(Router router);
	 Router removeRouter(ID id);
}
