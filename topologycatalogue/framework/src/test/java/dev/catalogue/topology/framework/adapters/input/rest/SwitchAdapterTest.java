package dev.catalogue.topology.framework.adapters.input.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.catalogue.topology.framework.adapters.input.rest.request.*;
import static dev.catalogue.topology.framework.adapters.input.rest.deserializers.SwitchDeserializer.getSwitchDeserialized;
import static dev.catalogue.topology.framework.adapters.input.rest.RouterAdapterTest.createLocation;
import static dev.catalogue.topology.framework.adapters.input.rest.deserializers.RouterDeserializer.getRouterDeserialized;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import java.io.IOException;
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwitchAdapterTest {
	@Test
    @Order(1)
    public void retrieveSwitch() throws IOException {
        var expectedSwitchId = "922dbcd5-d071-41bd-920b-00f83eb4bb46";
        var switchStr = given()
                .contentType("application/json")
                .pathParam("switchId", expectedSwitchId)
                .when()
                .get("/switch/{switchId}")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        var actualSwitchId = getSwitchDeserialized(switchStr).getId().getUuid().toString();

        assertEquals(expectedSwitchId, actualSwitchId);
    }
	@Test
    @Order(2)
    public void createAndAddSwitchToEdgeRouter() throws IOException {
        var expectedSwitchIP = "15.0.0.2";
        var edgeRouterId = "b07f5187-2d82-4975-a14b-bdbad9a8ad46";
        var createSwitch = SwitchRequest.builder()
                .vendor(Vendor.CISCO)
                .model(Model.ABC0001)
                .ip(expectedSwitchIP)
                .location(createLocation("United States"))
                .switchType(Switchtype.Layer3)
                .build();
        var routerStr = given()
                .contentType("application/json")
                .pathParam("edgeRouterId", edgeRouterId)
                .body(createSwitch)
                .when()
                .post("/switch/create/{edgeRouterId}")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        var switches = ((EdgeRouter)getRouterDeserialized(routerStr)).getSwitches();
        var containsSwitch = switches.values()
                .stream()
                .map(aSwitch -> aSwitch.getIp().getIpAddress())
                .anyMatch(ip->ip.equals(expectedSwitchIP));

        assertTrue(containsSwitch);
    }
	@Test
    @Order(3)
    public void removeSwitchFromEdgeRouter() throws IOException {
        var expectedSwitchId = "922dbcd5-d071-41bd-920b-00f83eb4bb47";
        var edgeRouterId = "b07f5187-2d82-4975-a14b-bdbad9a8ad46";

        var edgeRouterStr = given()
                .contentType("application/json")
                .pathParam("expectedSwitchId", expectedSwitchId)
                .pathParam("edgeRouterId", edgeRouterId)
                .when()
                .delete("/switch/{expectedSwitchId}/from/{edgeRouterId}")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        var switches = ((EdgeRouter)getRouterDeserialized(edgeRouterStr)).getSwitches();

        assertFalse(switches.containsKey(ID.withId(expectedSwitchId)));
    }
}
