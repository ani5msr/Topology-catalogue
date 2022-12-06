package dev.catalogue.topology.application.ports.input;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.application.ports.output.*;
import dev.catalogue.topology.application.status.RouterInfo;
import dev.catalogue.topology.domain.entity.factory.*;
import dev.catalogue.topology.application.usecases.*;
import lombok.NoArgsConstructor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;

@ApplicationScoped
public class RouterInputPort implements RouterUseCase{
	
	@Inject
	RouterOutputPort routerOutputPort;

	 @Override
	    public Router createRouter(ID id,
	                               Vendor vendor,
	                               Model model,
	                               IP ip,
	                               Location location,
	                               Routertype routerType) {
	        return Routerfactory.getRouter(null,
	                vendor,
	                model,
	                ip,
	                location,
	                routerType
	        );
	    }
    @Override
    public Router retrieveRouter(ID id) {
        return routerOutputPort.retrieveRouter(id);
    }
    @Override
    public Router removeRouter(ID id) {
        return routerOutputPort.removeRouter(id);
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
		 var addedRouter =  (CoreRouter) coreRouter.addRouter(router);
	     return addedRouter;
	}
	@Override
    public String getRouterStatus() {
        var routerInfo = new RouterInfo();
        return routerInfo.getRouterStatus();
    }
}
