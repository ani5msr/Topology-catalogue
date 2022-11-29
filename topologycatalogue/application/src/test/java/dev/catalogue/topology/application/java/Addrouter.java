package dev.catalogue.topology.application.java;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import dev.catalogue.topology.application.usecases.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
public class Addrouter extends Applicationtestdata {
		CoreRouter newcorerouter;
		public Addrouter(){
	        loadData();
	    }
	    @Given("I have an edge router")
	    public void assert_edge_router_exists(){
	        edgeRouter = (EdgeRouter) this.routerUseCase.createRouter(
	                Vendor.HP,
	                Model.ABC0004,
	                IP.fromAddress("20.0.0.1"),
	                locationA,
	                Routertype.Edge
	        );
	        assertNotNull(edgeRouter);
	    }
	    @And("I have a core router")
	    public void assert_core_router_exists(){
	        coreRouter = (CoreRouter) this.routerUseCase.createRouter(
	                Vendor.CISCO,
	                Model.ABC0001,
	                IP.fromAddress("30.0.0.1"),
	                locationA,
	                Routertype.Core
	        );
	        assertNotNull(coreRouter);
	    }
	    @Then("I add an edge router to a core router")
	    public void add_edge_to_core_router(){
	        var actualEdgeId = edgeRouter.getId();
	        var routerWithEdge = (CoreRouter) this.routerUseCase.addRoutertoCoreRouter(edgeRouter, coreRouter);
	        var expectedEdgeId = routerWithEdge.getRouters().get(actualEdgeId).getId();
	        assertEquals(actualEdgeId, expectedEdgeId);
	    }
	    //Adding a core router to another core router
	    @Given("I have this core router")
	    public void assert_this_core_router_exists(){
	        coreRouter = (CoreRouter) this.routerUseCase.createRouter(
	                Vendor.CISCO,
	                Model.ABC0001,
	                IP.fromAddress("30.0.0.1"),
	                locationA,
	                Routertype.Core
	        );
	        assertNotNull(coreRouter);
	    }
	    @And("I have another core router")
	    public void assert_another_core_router_exists(){
	        newcorerouter = (CoreRouter) this.routerUseCase.createRouter(
	                Vendor.CISCO,
	                Model.ABC0001,
	                IP.fromAddress("40.0.0.1"),
	                locationA,
	                Routertype.Core
	        );
	        assertNotNull(newcorerouter);
	    }
	    @Then("I add a core router to another core router")
	    public void add_core_to_core_router(){
	        var coreRouterId = coreRouter.getId();
	        var routerWithCore = (CoreRouter) this.routerUseCase.addRoutertoCoreRouter(coreRouter, newcorerouter);
	        var expectedCoreId = routerWithCore.getRouters().get(coreRouterId).getId();
	        assertEquals(coreRouterId, expectedCoreId);
	    }
}
