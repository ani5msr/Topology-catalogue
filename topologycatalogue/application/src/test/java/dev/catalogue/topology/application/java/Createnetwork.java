package dev.catalogue.topology.application.java;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import dev.catalogue.topology.domain.valueobj.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class Createnetwork extends Applicationtestdata{
	public Createnetwork(){
        loadData();
    }

    @Given("I provide all required data to create a network")
    public void i_provide_all_required_data_to_create_a_network(){
        network = networkUseCase.createNetwork(
                IP.fromAddress("10.0.0.1"),
                "Finance",
                8
        );
    }
    @Then("A new network is created")
    public void a_new_network_is_created(){
        assertNotNull(network);
        assertEquals(IP.fromAddress("10.0.0.1"), network.getNetworkAddress());
        assertEquals("Finance", network.getNetworkName());
        assertEquals(8, network.getNetworkCidr());
    }
}
