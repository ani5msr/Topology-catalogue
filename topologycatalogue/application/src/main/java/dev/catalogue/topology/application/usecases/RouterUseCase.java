package dev.catalogue.topology.application.usecases;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
public interface RouterUseCase {
	Router createRouter(Vendor vendor,
            Model model,
            IP ip,
            Location location,
            Routertype routerType);
	CoreRouter addRoutertoCoreRouter(Router router, CoreRouter coreRouter);
	Router removeRouterFromCoreRouter(
            Router router, CoreRouter coreRouter);

    Router retrieveRouter(ID id);

    Router persistRouter(Router router);
}
