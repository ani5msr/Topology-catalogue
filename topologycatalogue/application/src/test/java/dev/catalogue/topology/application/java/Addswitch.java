package dev.catalogue.topology.application.java;
import io.cucumber.java.en.Given;
import javax.inject.Inject;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dev.catalogue.topology.application.usecases.SwitchUseCase;
import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.ID;
import dev.catalogue.topology.domain.valueobj.IP;
import dev.catalogue.topology.domain.valueobj.Model;
import dev.catalogue.topology.domain.valueobj.Switchtype;
import dev.catalogue.topology.domain.valueobj.Vendor;
public class Addswitch extends Applicationtestdata{
	@Inject
	SwitchUseCase switchUseCase;
	public Addswitch() {
        loadData();
    }
	 @Given("I provide a switch")
	    public void i_provide_a_switch(){
	        networkSwitch = Switch.builder().
	        		switchId(ID.withoutId()).
	                vendor(Vendor.CISCO).
	                model(Model.ABC0004).
	                ip(IP.fromAddress("20.0.0.100")).
	                location(locationA).
	                switchType(Switchtype.Layer3).
	                build();
	        assertNotNull(networkSwitch);
	    }
	 @Then("I add the switch to the edge router")
	    public void i_add_the_switch_to_the_edge_router(){
	        assertNotNull(edgeRouter);
	        edgeRouter = this.switchUseCase.
	                addSwitchToEdgeRouter(networkSwitch, edgeRouter);
	        var actualId = networkSwitch.getId();
	        var expectedId = edgeRouter.
	                getSwitches().
	                get(ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
	                getId();
	        assertEquals(expectedId, actualId);
	    }
}
