package dev.catalogue.topology.application.ports.input;

import dev.catalogue.topology.application.usecases.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
public class SwitchInputPort implements SwitchUseCase {
	 @Override
	    public Switch createSwitch(
	            Vendor vendor,
	            Model model,
	            IP ip,
	            Location location,
	            Switchtype switchType) {
	        return Switch
	                .builder()
	                .id(ID.withoutId())
	                .vendor(vendor)
	                .model(model)
	                .ip(ip)
	                .location(location)
	                .switchType(switchType)
	                .build();
	    }
	 @Override
	    public EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
	        edgeRouter.addSwitch(networkSwitch);
	        return edgeRouter;
	    }
	 @Override
	    public EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
	        edgeRouter.removeSwitch(networkSwitch);
	        return edgeRouter;
	    }
}
