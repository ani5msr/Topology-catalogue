package dev.catalogue.topology.application.java;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.function.Predicate;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dev.catalogue.topology.domain.service.Networkservice;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.application.usecases.*;
public class Removenetwork extends Applicationtestdata{
	Predicate <Network> pred;
	public Removenetwork(){
        loadData();
    }
	  @Given("I know the network I want to remove")
	    public void i_know_the_network_i_want_to_remove(){
	        pred = Network.getNetworkNamePredicate("TestNetwork");
	        network = Networkservice.findNetwork(networks, pred);
	        assertEquals("TestNetwork", network.getNetworkName());
	    }
	  @And("I have a switch to remove a network")
	    public void i_have_a_switch_to_remove_a_network(){
	        assertNotNull(networkSwitch);
	    }
	  @Then("I remove the network from the switch")
	    public void i_remove_the_network_from_the_switch(){
	        this.networkUseCase.removeNetworkFromSwitch(network, networkSwitch);
	        network = Networkservice.findNetwork(networks, pred);
	        assertNull(network);
	    }
}
