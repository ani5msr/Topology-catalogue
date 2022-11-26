package dev.catalogue.topology.application.ports.input;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.application.ports.output.*;
import dev.catalogue.topology.domain.entity.factory.*;
import dev.catalogue.topology.application.usecases.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RouterInputPort implements RouterUseCase{
	RouterOutputPort routerOutputPort;

    @Override
    public Router createRouter(Vendor vendor,
                               Model model,
                               IP ip,
                               Location location,
                               Routertype routerType) {
        return Routerfactory.getRouter(null,
                vendor, model, ip, location, routerType);
    }
    @Override
    public Router retrieveRouter(ID id) {
        return routerOutputPort.retrieveRouter(id);
    }

    @Override
    public Router persistRouter(Router router) {
        return routerOutputPort.persistRouter(router);
    }
    @Override
    public Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter) {
        var removedRouter = coreRouter.removeRouter(router);
        return removedRouter;
    }
	@Override
	public CoreRouter addRoutertoCoreRouter(Router router, CoreRouter coreRouter) {
		 CoreRouter addedRouter =  (CoreRouter) coreRouter.addRouter(router);
	     return addedRouter;
	}
}
