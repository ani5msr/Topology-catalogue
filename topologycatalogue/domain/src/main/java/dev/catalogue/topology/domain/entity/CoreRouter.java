package dev.catalogue.topology.domain.entity;
import dev.catalogue.topology.domain.specification.*;
import dev.catalogue.topology.domain.valueobj.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Map;
@Getter
@ToString
public class CoreRouter extends Router{
	 @Setter
	 private Map<ID, Router> routers;
	 @Builder
	 public CoreRouter(ID id, Vendor vendor, Model model, IP ip, Location location, Routertype routerType, Map<ID, Router> routers) {
	        super(id, vendor, model, ip, location, routerType);
	        this.routers = routers;
	 }
	 public Router addRouter(Router anyRouter) {
	        var sameCountryRouterSpec = new SameCountrySpecification(this);
	        var sameIpSpec = new SameIPSpecification(this);
	        sameCountryRouterSpec.check(anyRouter);
	        sameIpSpec.check(anyRouter);
	        return this.routers.put(anyRouter.id, anyRouter);
	    }
	 public Router removeRouter(Router anyRouter) {
	        var emptyRoutersSpec = new EmptyRouterSpecification();
	        var emptySwitchSpec = new EmptySwitchSpecification();

	        switch (anyRouter.routertype) {
	            case Core:
	                var coreRouter = (CoreRouter)anyRouter;
	                emptyRoutersSpec.check(coreRouter);
	                break;
	            case Edge:
	                var edgeRouter = (EdgeRouter)anyRouter;
	                emptySwitchSpec.check(edgeRouter);
	        }
	        return this.routers.remove(anyRouter.id);
	    }
	public Map<ID, Router> getRouters() {
		return this.routers;
	}
	 
}
