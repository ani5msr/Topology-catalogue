package dev.catalogue.topology.application.java;

import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.application.usecases.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.application.ports.input.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Applicationtestdata {
	    protected Router router;
	    protected List<Router> routers = new ArrayList<>();
	    protected List<Switch> switches = new ArrayList<>();
	    protected List<Network> networks = new ArrayList<>();
	    protected Map<ID, Router> routersOfCoreRouter = new HashMap<>();
	    protected Map<ID, Switch> switchesOfEdgeRouter = new HashMap<>();
	    protected Network network;
	    protected Switch networkSwitch;
	    protected CoreRouter coreRouter;
	    protected CoreRouter newCoreRouter;
	    protected EdgeRouter edgeRouter;
	    protected EdgeRouter newEdgeRouter;
	    protected Location locationA;
	    protected Location locationB;
	    public void loadData(){
	        this.locationA = Location.builder().
	                address("Av Republica Argentina 3109").
	                city("Curitiba").
	                state("PR").
	                country("Brazil").
	                zipCode(80610260).
	                latitude(10F).
	                longitude(-10F).
	                build();
	        this.locationB = Location.builder().
	                address("Av Republica Argentina 3109").
	                city("Curitiba").
	                state("PR").
	                country("Brazil").
	                zipCode(80610260).
	                latitude(10F).
	                longitude(-10F).
	                build();
	        this.network  = Network.builder().
	                networkAddress(IP.fromAddress("20.0.0.0")).
	                networkName("TestNetwork").
	                networkCidr(8).
	                build();
	        this.networks.add(network);
	        this.networkSwitch = Switch.builder().
	                switchId(ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0004).
	                ip(IP.fromAddress("20.0.0.100")).
	                location(locationA).
	                switchType(Switchtype.Layer3).
	                switchNetworks(networks).
	                build();
	        this.switchesOfEdgeRouter.put(networkSwitch.getId(), networkSwitch);
	        this.edgeRouter = EdgeRouter.builder().
	                id(ID.withoutId()).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0002).
	                ip(IP.fromAddress("20.0.0.1")).
	                location(locationA).
	                routerType(Routertype.Edge).
	                switches(switchesOfEdgeRouter).
	                build();
	        this.routersOfCoreRouter.put(edgeRouter.getId(), edgeRouter);
	        this.coreRouter = CoreRouter.builder().
	                id(ID.withoutId()).
	                vendor(Vendor.HP).
	                model(Model.ABC0001).
	                ip(IP.fromAddress("10.0.0.1")).
	                location(locationA).
	                routerType(Routertype.Core).
	                routers(routersOfCoreRouter).
	                build();
	        this.newCoreRouter = CoreRouter.builder().
	                id(ID.withId("81579b05-4b4e-4b9b-91f4-75a5a8561296")).
	                vendor(Vendor.HP).
	                model(Model.ABC0001).
	                ip(IP.fromAddress("10.1.0.1")).
	                location(locationA).
	                routerType(Routertype.Core).
	                build();
	        this.coreRouter.addRouter(newCoreRouter);
	        this.newEdgeRouter = EdgeRouter.builder().
	                id(ID.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0002).
	                ip(IP.fromAddress("20.1.0.1")).
	                location(locationA).
	                routerType(Routertype.Edge).
	                build();
	    }
}
