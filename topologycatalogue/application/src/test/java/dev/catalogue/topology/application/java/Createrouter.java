package dev.catalogue.topology.application.java;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import dev.catalogue.topology.domain.valueobj.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class Createrouter extends Applicationtestdata{
    public Createrouter(){
        loadData();
    }
    @Given("I provide all required data to create a core router")
    public void create_core_router(){
        router = this.routerUseCase.createRouter(
                Vendor.CISCO,
                Model.ABC0001,
                IP.fromAddress("20.0.0.1"),
                locationA,
                Routertype.Core
        );
    }
    @Then("A new core router is created")
    public void a_new_core_router_is_created(){
        assertNotNull(router);
        assertEquals(Routertype.Core, router.getRoutertype());
    }
  //Creating a new edge router
    @Given("I provide all required data to create an edge router")
    public void create_edge_router(){
        router = this.routerUseCase.createRouter(
                Vendor.HP,
                Model.ABC0004,
                IP.fromAddress("30.0.0.1"),
                locationA,
                Routertype.Edge
        );
    }
    @Then("A new edge router is created")
    public void a_new_edge_router_is_created(){
        assertNotNull(router);
        assertEquals(Routertype.Edge, router.getRoutertype());
    }
}
