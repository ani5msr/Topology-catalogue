package dev.catalogue.topology.framework.adapters.input.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.wildfly.common.Assert.assertFalse;

import java.io.IOException;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import dev.catalogue.topology.framework.adapters.input.rest.request.NetworkRequest;
import static dev.catalogue.topology.framework.adapters.input.rest.deserializers.SwitchDeserializer.getSwitchDeserialized;
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkAdapterTest {
	 @Test
	    @Order(1)
	    public void addNetworkToSwitch() throws IOException {
	        var networkName = "TestNetwork";
	        var switchId = "922dbcd5-d071-41bd-920b-00f83eb4bb46";
	        var addNetwork = NetworkRequest.builder()
	                .networkAddress("60.0.0.1")
	                .networkName(networkName)
	                .networkCidr(24)
	                .build();
	        var switchStr = given()
	                .contentType("application/json")
	                .pathParam("switchId", switchId)
	                .body(addNetwork)
	                .when()
	                .post("/network/add/{switchId}")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();
	        var networks = getSwitchDeserialized(switchStr).getSwitchNetworks();
	        var networkExists = networks
	                .stream()
	                .map(network -> network.getNetworkName())
	                .anyMatch(name -> name.equals(networkName));

	        assertTrue(networkExists);
	    }
	 @Test
	    @Order(2)
	    public void removeNetworkFromSwitch() throws IOException {
	        var networkName = "Engineering";
	        var switchId = "922dbcd5-d071-41bd-920b-00f83eb4bb46";

	        var switchStr = given()
	                .contentType("application/json")
	                .pathParam("networkName", networkName)
	                .pathParam("switchId", switchId)
	                .when()
	                .delete("/network/{networkName}/from/{switchId}")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();

	        var networks = getSwitchDeserialized(switchStr).getSwitchNetworks();
	        var networkExists = networks
	                .stream()
	                .map(network -> network.getNetworkName())
	                .anyMatch(name -> name.equals(networkName));

	        assertFalse(networkExists);
	    }
}
