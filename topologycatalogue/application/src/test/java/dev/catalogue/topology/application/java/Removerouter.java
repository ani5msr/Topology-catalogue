package dev.catalogue.topology.application.java;

import dev.catalogue.topology.domain.entity.CoreRouter;
import dev.catalogue.topology.domain.entity.Router;
import dev.catalogue.topology.domain.valueobj.Routertype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dev.catalogue.topology.application.usecases.RouterUseCase;
import dev.catalogue.topology.domain.entity.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javax.inject.Inject;
public class Removerouter extends Applicationtestdata{
	@Inject 
	RouterUseCase routerUseCase;
	CoreRouter coreRouterToBeRemoved;

    public Removerouter(){
        loadData();
    }
    @Given("The core router has at least one edge router connected to it")
    public void the_core_router_has_at_least_one_edge_router_connected_to_it(){
        var predicate = Router.getRouterTypePredicate(Routertype.Edge);
        edgeRouter = (EdgeRouter) this.coreRouter.
                getRouters().
                entrySet().
                stream().
                map(routerMap -> routerMap.getValue()).
                filter(predicate).
                findFirst().
                get();
        assertEquals(Routertype.Edge, edgeRouter.getRoutertype());
    }
    @And("The switch has no networks attached to it")
    public void the_switch_has_no_networks_attached_to_it(){
        var networksSize = networkSwitch.getSwitchNetworks().size();
        assertEquals(1, networksSize);
        networkSwitch.removeNetworkFromSwitch(network);
        networksSize = networkSwitch.getSwitchNetworks().size();
        assertEquals(0, networksSize);
    }
    @And("The edge router has no switches attached to it")
    public void the_edge_router_has_no_switches_attached_to_it(){
        var switchesSize = edgeRouter.getSwitches().size();
        assertEquals(1, switchesSize);
        edgeRouter.removeSwitch(networkSwitch);
        switchesSize = edgeRouter.getSwitches().size();
        assertEquals(0, switchesSize);
    }
    @Then("I remove the edge router from the core router")
    public void edge_router_is_removed_from_core_router(){
        var actualID = edgeRouter.getId();
        var expectedID = this.routerUseCase.removeRouterFromCoreRouter(edgeRouter, coreRouter).getId();
        assertEquals(expectedID, actualID);
    }
  //Removing a core router from another core router
    @Given("The core router has at least one core router connected to it")
    public void the_core_router_has_at_least_one_core_router_connected_to_it(){
        var predicate = Router.getRouterTypePredicate(Routertype.Core);
        coreRouterToBeRemoved = (CoreRouter) this.coreRouter.
                getRouters().
                entrySet().
                stream().
                map(routerMap -> routerMap.getValue()).
                filter(predicate).
                findFirst().
                get();
        assertEquals(Routertype.Core, coreRouterToBeRemoved.getRoutertype());
    }
    @And("The core router has no other routers connected to it")
    public void the_core_router_no_other_routers_connected_to_it(){
        assertTrue(coreRouterToBeRemoved.getRouters().isEmpty());
    }
    @Then("I remove the core router from another core router")
    public void i_remove_the_core_router_from_another_core_router(){
        var actualId = coreRouterToBeRemoved.getId();
        var expectedId =  this.coreRouter.removeRouter(coreRouterToBeRemoved).getId();
        assertEquals(expectedId, actualId);
    }
}
