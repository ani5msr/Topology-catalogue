package dev.catalogue.topology.application.usecases;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
public interface SwitchUseCase {
	 Switch createSwitch(
	            Vendor vendor,
	            Model model,
	            IP ip,
	            Location location,
	            Switchtype switchType
	            );
	 EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter);

	 EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter);

}
