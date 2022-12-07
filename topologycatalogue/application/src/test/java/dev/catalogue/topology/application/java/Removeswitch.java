package dev.catalogue.topology.application.java;
import io.cucumber.java.en.And;
import javax.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import dev.catalogue.topology.application.usecases.SwitchUseCase;
import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.ID;
public class Removeswitch extends Applicationtestdata{
	@Inject
	SwitchUseCase switchUseCase;
	ID id;
	Switch switchToBeRemoved;
	public Removeswitch() {
		loadData();
	}
	 @Given("I know the switch I want to remove")
	    public void i_know_the_switch_i_want_to_remove(){
	        id = ID.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490");
	        switchToBeRemoved = edgeRouter.getSwitches().get(id);
	    }
	    @And("The switch has no networks")
	    public void the_switch_has_no_networks() {
	        switchToBeRemoved.removeNetworkFromSwitch(network);
	        assertTrue(switchToBeRemoved.getSwitchNetworks().isEmpty());
	    }
	    @Then("I remove the switch from the edge router")
	    public void i_remove_the_switch_from_the_edge_router(){
	        assertNotNull(edgeRouter);
	        edgeRouter = this.switchUseCase.removeSwitchFromEdgeRouter(switchToBeRemoved, edgeRouter);
	        assertNull(edgeRouter.getSwitches().get(id));
	    }
}
