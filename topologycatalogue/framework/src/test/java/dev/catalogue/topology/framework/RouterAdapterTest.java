package dev.catalogue.topology.framework;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.framework.adapters.input.rest.request.router.*;
import static dev.catalogue.topology.framework.adapters.input.rest.deserializers.RouterDeserializer.getRouterDeserialized;
import java.io.IOException;
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RouterAdapterTest {
	    @Test
	    @Order(1)
	    public void retrieveRouter() throws IOException {
	        var expectedRouterId = "b832ef4f-f894-4194-8feb-a99c2cd4be0c";
	        var routerStr = given()
	                .contentType("application/json")
	                .pathParam("routerId", expectedRouterId)
	                .when()
	                .get("/router/{routerId}")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();
	        var actualRouterId = getRouterDeserialized(routerStr).getId().getUuid().toString();
	        assertEquals(expectedRouterId, actualRouterId);
	    }
	    @Test
	    @Order(2)
	    public void createRouter() throws IOException {
	        var expectedIpAddress = "25.0.0.1";
	        var createRouter = RouterRequest.builder()
	                .vendor(Vendor.HP)
	                .model(Model.ABC0004)
	                .ip(expectedIpAddress)
	                .location(createLocation("United States"))
	                .routerType(Routertype.Core).build();
	        var routerStr = given()
	                .contentType("application/json")
	                .body(createRouter)
	                .when()
	                .post("/router")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();
	        var actualIpAddress = getRouterDeserialized(routerStr).getIp().getIpAddress();

	        assertEquals(expectedIpAddress, actualIpAddress);
	    }
	    @Test
	    @Order(3)
	    public void addRouterToCoreRouter() throws IOException {
	        var expectedRouterId = "b832ef4f-f894-4194-8feb-a99c2cd4be0d";
	        var coreRouterId = "b832ef4f-f894-4194-8feb-a99c2cd4be0c";

	        var coreRouterStr = given()
	                .contentType("application/json")
	                .pathParam("routerId", expectedRouterId)
	                .pathParam("coreRouterId", coreRouterId)
	                .when()
	                .post("/router/{routerId}/to/{coreRouterId}")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();
	        var routers = ((CoreRouter) getRouterDeserialized(coreRouterStr)).getRouters();

	        assertTrue(routers.containsKey(ID.withId(expectedRouterId)));
	    }
	    
	    @Test
	    @Order(4)
	    public void removeRouterFromCoreRouter() throws IOException {
	        var expectedRouterId = "b832ef4f-f894-4194-8feb-a99c2cd4be0a";
	        var coreRouterId = "b832ef4f-f894-4194-8feb-a99c2cd4be0c";

	        var removedRouterStr = given()
	                .contentType("application/json")
	                .pathParam("routerId", expectedRouterId)
	                .pathParam("coreRouterId", coreRouterId)
	                .when()
	                .delete("/router/{routerId}/from/{coreRouterId}")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();
	        var removedRouterId = getRouterDeserialized(removedRouterStr).getId().getUuid().toString();

	        assertEquals(expectedRouterId, removedRouterId);
	    }
	    @Test
	    @Order(5)
	    public void removeRouter() {
	        var routerId = "b832ef4f-f894-4194-8feb-a99c2cd4be0b";
	        var routerStr = given()
	                .contentType("application/json")
	                .pathParam("routerId", routerId)
	                .when()
	                .delete("/router/{routerId}")
	                .then()
	                .statusCode(200)
	                .extract()
	                .asString();

	        assertTrue(routerStr.isEmpty());
	    }

	    public static Location createLocation(String country){
	        return Location.builder().
	                address("Test street").
	                city("Test City").
	                state("Test State").
	                country(country).
	                zipCode(00000).
	                latitude(10F).
	                longitude(-10F).
	                build();
	    }
	    
	   

}
