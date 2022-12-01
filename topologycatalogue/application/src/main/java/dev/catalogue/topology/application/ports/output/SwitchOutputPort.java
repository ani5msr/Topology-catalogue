package dev.catalogue.topology.application.ports.output;

import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.*;
public interface SwitchOutputPort {
	 Switch retrieveSwitch(ID id);
}
