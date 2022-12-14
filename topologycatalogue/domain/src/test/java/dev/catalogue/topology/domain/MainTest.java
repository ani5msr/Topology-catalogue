package dev.catalogue.topology.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.catalogue.topology.domain.valueobj.*;
import org.junit.jupiter.api.BeforeEach;
import dev.catalogue.topology.domain.service.*;
import org.junit.jupiter.api.Test;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.exception.GenericException;
import dev.catalogue.topology.domain.specification.common.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class MainTest {
	@Test
	 public Location createLocation(String country){
	        return Location.builder().
	                address("Test street").
	                city("Test City").
	                state("Test State").
	                country(country).
	                zipCode(00000).
	                latitude(10F).
	                longitude(-10F).
	                build();
	    }
	@Test
	 public List<Network> createNetworks(Network network){
	        List<Network> networks = new ArrayList<>();
	        networks.add(network);
	        return networks;
	    }
	  private Switch createNetworkSwitch(Location location, List<Network> networks){
	        return Switch.builder().
	                switchId(ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0004).
	                ip(IP.fromAddress("20.0.0.100")).
	                location(location).
	                switchType(Switchtype.Layer3).
	                switchNetworks(networks).
	                build();
	    }
	@Test
	  public Network createTestNetwork(String address, int CIDR){
	        return Network.builder().
	                networkAddress(IP.fromAddress(address)).
	                networkName("NewNetwork").
	                networkCidr(CIDR).
	                build();
	    }
	@Test
	  public Switch createSwitch(String address, int cidr, Location location){
	        var newNetwork = createTestNetwork(address, cidr);
	        var networks = createNetworks(newNetwork);
	        var networkSwitch = createNetworkSwitch(location, networks);
	        return networkSwitch;
	    }
	@Test
	  public EdgeRouter createEdgeRouter(Location location, String address){
	        Map<ID, Switch> switchesOfEdgeRouter = new HashMap<>();
	        return EdgeRouter.builder().
	                id(ID.withoutId()).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0002).
	                ip(IP.fromAddress(address)).
	                location(location).
	                routerType(Routertype.Edge).
	                switches(switchesOfEdgeRouter).
	                build();
	    }
	@Test
	    public CoreRouter createCoreRouter(Location location, String address){
	        Map<ID, Router> routersOfCoreRouter = new HashMap<>();
	        return CoreRouter.builder().
	                id(ID.withoutId()).
	                vendor(Vendor.HP).
	                model(Model.ABC0001).
	                ip(IP.fromAddress(address)).
	                location(location).
	                routerType(Routertype.Core).
	                routers(routersOfCoreRouter).
	                build();
	    }
	    @Test
	    public void filterRouterByVendor(){
	        List<Router> routers = new ArrayList<>();
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");
	        var edgeRouter = createEdgeRouter(location, "40.0.0.1");

	        routers.add(coreRouter);
	        routers.add(edgeRouter);

	        var actualVendor = Routerservice.filterAndRetrieveRouter(routers,
	                Router.getVendorPredicate(Vendor.HP)).get(0).getVendor();
	        assertEquals(Vendor.HP, actualVendor);

	        actualVendor = Routerservice.filterAndRetrieveRouter(routers,
	                Router.getVendorPredicate(Vendor.CISCO)).get(0).getVendor();
	        assertEquals(Vendor.CISCO, actualVendor);
	    }

	    @Test
	    public void filterRouterByLocation(){
	        List<Router> routers = new ArrayList<>();
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");

	        routers.add(coreRouter);

	        var actualCountry = Routerservice.filterAndRetrieveRouter(routers,
	                Router.getCountryPredicate(location)).get(0).getLocation().getCountry();
	        assertEquals(location.getCountry(), actualCountry);
	    }
	    @Test
	    public void filterRouterByModel(){
	        List<Router> routers = new ArrayList<>();
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");
	        var newCoreRouter = createCoreRouter(location, "40.0.0.1");

	        coreRouter.addRouter(newCoreRouter);
	        routers.add(coreRouter);

	        var actualModel= Routerservice.filterAndRetrieveRouter(routers,
	                Router.getModelPredicate(Model.ABC0001)).get(0).getModel();
	        assertEquals(Model.ABC0001, actualModel);
	    }
	    @Test
	    public void filterSwitchByType(){
	        List<Switch> switches = new ArrayList<>();
	        var location = createLocation("US");
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);

	        switches.add(networkSwitch);

	        var actualSwitchType = Switchservice.filterAndRetrieveSwitch(switches,
	                Switch.getSwitchTypePredicate(Switchtype.Layer3)).get(0).getSwitchType();
	        assertEquals(Switchtype.Layer3, actualSwitchType);
	    }
	    @Test
	    public void filterNetworkByProtocol(){
	        var testNetwork = createTestNetwork("30.0.0.0", 8);
	        var networks = createNetworks(testNetwork);

	        var expectedProtocol = Protocol.IPV4;
	        var actualProtocol = Networkservice.filterAndRetrieveNetworks(networks,
	                Switch.getNetworkProtocolPredicate(Protocol.IPV4)).get(0).getNetworkAddress().getProtocol();
	        assertEquals(expectedProtocol, actualProtocol);
	    }
	    @Test
	    public void findRouterById(){
	        List<Router> routers = new ArrayList<>();
	        Map<ID, Router> routersOfCoreRouter = new HashMap<>();
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");
	        var newCoreRouter = createCoreRouter(location, "40.0.0.1");


	        coreRouter.addRouter(newCoreRouter);
	        routersOfCoreRouter.put(newCoreRouter.getId(), newCoreRouter);

	        var expectedId = newCoreRouter.getId();
	        var actualId = Routerservice.findById(routersOfCoreRouter, expectedId).getId();
	        assertEquals(expectedId, actualId);
	    }
	    @Test
	    public void findSwitchById(){
	        List<Switch> switches = new ArrayList<>();
	        Map<ID, Switch> switchesOfEdgeRouter = new HashMap<>();
	        var location = createLocation("US");
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);

	        switchesOfEdgeRouter.put(networkSwitch.getId(), networkSwitch);

	        var expectedId = ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490");
	        var actualId = Switchservice.findById(switchesOfEdgeRouter, expectedId).getId();
	        assertEquals(expectedId, actualId);
	    }
	    @Test
	    public void addNetworkToSwitch(){
	        var location = createLocation("US");
	        var newNetwork = createTestNetwork("30.0.0.1", 8);
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);
	        assertTrue(networkSwitch.addNetworkToSwitch(newNetwork));
	    }
	    @Test
	    public void addNetworkToSwitch_failBecauseSameNetworkAddress(){
	        var location = createLocation("US");
	        var newNetwork = createTestNetwork("30.0.0.0", 8);
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);
	        assertThrows(GenericException.class, () -> networkSwitch.addNetworkToSwitch(newNetwork));
	    }
	    @Test
	    public void addSwitchToEdgeRouter(){
	        var location = createLocation("US");
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);
	        var edgeRouter = createEdgeRouter(location,"30.0.0.1");

	        edgeRouter.addSwitch(networkSwitch);

	        assertEquals(1,edgeRouter.getSwitches().size());
	    }
	    @Test
	    public void addEdgeToCoreRouter(){
	        var location = createLocation("US");
	        var edgeRouter = createEdgeRouter(location,"30.0.0.1");
	        var coreRouter = createCoreRouter(location, "40.0.0.1");

	        coreRouter.addRouter(edgeRouter);

	        assertEquals(1,coreRouter.getRouters().size());
	    }
	    @Test
	    public void addCoreToCoreRouter(){
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");
	        var newCoreRouter = createCoreRouter(location, "40.0.0.1");

	        coreRouter.addRouter(newCoreRouter);

	        assertEquals(1,coreRouter.getRouters().size());
	    }

	    @Test
	    public void addCoreToCoreRouter_failBecauseRoutersOfSameIp(){
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");
	        var newCoreRouter = createCoreRouter(location, "30.0.0.1");

	        assertThrows(GenericException.class, () -> coreRouter.addRouter(newCoreRouter));
	    }
	    @Test
	    public void removeRouter(){
	        var location = createLocation("US");
	        var coreRouter = createCoreRouter(location, "30.0.0.1");
	        var edgeRouter = createEdgeRouter(location, "40.0.0.1");
	        var expectedId = edgeRouter.getId();

	        coreRouter.addRouter(edgeRouter);
	        var actualId = coreRouter.removeRouter(edgeRouter).getId();

	        assertEquals(expectedId, actualId);
	    }

	    @Test
	    public void removeSwitch(){
	        var location = createLocation("US");
	        var network = createTestNetwork("30.0.0.0", 8);
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);
	        var edgeRouter = createEdgeRouter(location, "40.0.0.1");

	        edgeRouter.addSwitch(networkSwitch);
	        networkSwitch.removeNetworkFromSwitch(network);
	        var expectedId = ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490");
	        var actualId= edgeRouter.removeSwitch(networkSwitch).getId();

	        assertEquals(expectedId, actualId);
	    }

	    @Test
	    public void removeNetwork(){
	        var location = createLocation("US");
	        var network = createTestNetwork("30.0.0.0", 8);
	        var networkSwitch = createSwitch("30.0.0.0", 8, location);

	        assertEquals(1, networkSwitch.getSwitchNetworks().size());
	        assertTrue(networkSwitch.removeNetworkFromSwitch(network));
	        assertEquals(0, networkSwitch.getSwitchNetworks().size());
	    }


}
