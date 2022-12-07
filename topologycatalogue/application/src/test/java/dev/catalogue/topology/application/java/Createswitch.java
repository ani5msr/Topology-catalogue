package dev.catalogue.topology.application.java;

import io.cucumber.java.en.Given;
import javax.inject.Inject;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dev.catalogue.topology.application.usecases.NetworkUseCase;
import dev.catalogue.topology.application.usecases.SwitchUseCase;
import dev.catalogue.topology.domain.valueobj.IP;
import dev.catalogue.topology.domain.valueobj.Model;
import dev.catalogue.topology.domain.valueobj.Switchtype;
import dev.catalogue.topology.domain.valueobj.Vendor;
public class Createswitch extends Applicationtestdata {
	   @Inject
	   SwitchUseCase switchUseCase;
	   public Createswitch(){
	        loadData();
	    }
	   @Given("I provide all required data to create a switch")
	    public void i_provide_all_required_data_to_create_a_switch(){
	        networkSwitch = this.switchUseCase.createSwitch(
	                Vendor.CISCO,
	                Model.ABC0001,
	                IP.fromAddress("20.0.0.100"),
	                locationA,
	                Switchtype.Layer3
	        );
	    }

	    @Then("A new switch is created")
	    public void a_new_switch_is_created() {
	        assertNotNull(networkSwitch);
	        assertEquals(Vendor.CISCO, networkSwitch.getVendor());
	        assertEquals(Model.ABC0001,networkSwitch.getModel());
	        assertEquals(IP.fromAddress("20.0.0.100"), networkSwitch.getIp());
	        assertEquals(locationA, networkSwitch.getLocation());
	        assertEquals(Switchtype.Layer3, networkSwitch.getSwitchType());
	    }
}
