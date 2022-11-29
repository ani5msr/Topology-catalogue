package dev.catalogue.topology.application.java;

import dev.catalogue.topology.application.usecases.*;
import dev.catalogue.topology.domain.service.Networkservice;
import dev.catalogue.topology.domain.valueobj.IP;
import dev.catalogue.topology.domain.valueobj.Network;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class Addnetwork extends Applicationtestdata {
    public Addnetwork(){
        loadData();
    }
    @Given("I have a network")
    public void i_have_a_network(){
        network = networkUseCase.createNetwork(
                IP.fromAddress("10.0.0.1"),
                "Finance",
                8
        );
        assertNotNull(network);
    }

    @And("I have a switch to add a network")
    public void i_have_a_switch_to_add_a_network(){
        assertNotNull(networkSwitch);
    }

    @Then("I add the network to the switch")
    public void i_add_the_network_to_the_switch(){
        var predicate = Network.getNetworkNamePredicate("Finance");
        var networks = this.
                networkUseCase.
                addNetworkToSwitch(network, networkSwitch).
                getSwitchNetworks();
        var network = Networkservice.
                findNetwork(networks, predicate).getNetworkName();
        assertEquals("Finance", network);
    }
}
