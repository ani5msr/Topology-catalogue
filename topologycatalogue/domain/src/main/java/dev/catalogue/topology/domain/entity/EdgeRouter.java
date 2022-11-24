package dev.catalogue.topology.domain.entity;

import java.util.Map;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.specification.*;
import lombok.Builder;
import lombok.Getter;

public class EdgeRouter extends Router {
	@Getter
	private Map<ID, Switch> switches;
    @Builder
	public EdgeRouter(ID id, Vendor vendor, Model model, IP ip, Location location, Routertype routerType, Map<ID, Switch> switches) {
	        super(id, vendor, model, ip, location, routerType);
	        this.switches = switches;
	    }
    public void addSwitch(Switch anySwitch) {
        var sameCountryRouterSpec = new SameCountrySpecification(this);
        var sameIpSpec = new SameIPSpecification(this);
        sameCountryRouterSpec.check(anySwitch);
        sameIpSpec.check(anySwitch);
        this.switches.put(anySwitch.id,anySwitch);
    }
    public Switch removeSwitch(Switch anySwitch) {
        var emptyNetworkSpec = new EmptyNetworkSpecification();
        emptyNetworkSpec.check(anySwitch);

        return this.switches.remove(anySwitch.id);
    }
	
}
