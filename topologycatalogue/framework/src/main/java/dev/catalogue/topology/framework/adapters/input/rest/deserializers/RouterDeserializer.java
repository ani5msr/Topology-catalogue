package dev.catalogue.topology.framework.adapters.input.rest.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.factory.*;

import static dev.catalogue.topology.framework.adapters.input.rest.deserializers.LocationDeserializer.getLocation;
import static dev.catalogue.topology.framework.adapters.input.rest.deserializers.SwitchDeserializer.getSwitchDeserialized;
public class RouterDeserializer extends StdDeserializer<Router>{
	public RouterDeserializer() {
        this(null);
    }

    public RouterDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Router deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var id = node.get("id").get("uuid").asText();
        var vendor = node.get("vendor").asText();
        var model = node.get("model").asText();
        var ip = node.get("ip").get("ipAddress").asText();
        var location = node.get("location");
        var routerType = Routertype.valueOf(node.get("routerType").asText());
        var routersNode = node.get("routers");
        var switchesNode = node.get("switches");

        var router = Routerfactory.getRouter(
                ID.withId(id),
                Vendor.valueOf(vendor),
                Model.valueOf(model),
                IP.fromAddress(ip),
                getLocation(location),
                routerType);

        fetchChildRouters(routerType, routersNode, router);
        fetchChildSwitches(routerType, switchesNode, router);

        return router;
    }

    private void fetchChildRouters(Routertype routerType, JsonNode routersNode, Router router) throws IOException {
        Map<ID, Router> routers = new HashMap<>();
        if (routerType==Routertype.Core && routers != null) {
            Iterator<String> childRouters = routersNode.fieldNames();
            while (childRouters.hasNext()) {
                String childRouter = childRouters.next();
                JsonNode routerJsonNode = routersNode.get(childRouter);
                var fetchedRouter = getRouterDeserialized(routerJsonNode.toString());
                routers.put(fetchedRouter.getId(), fetchedRouter);
            }
            ((CoreRouter)router).setRouters(routers);
        }
    }

    private void fetchChildSwitches(Routertype routerType, JsonNode switchesNode, Router router) throws IOException {
        Map<ID, Switch> switches = new HashMap<>();
        if (routerType==Routertype.Edge && switches != null) {
            var childSwitches = switchesNode.fieldNames();
            while (childSwitches.hasNext()) {
                var childSwitch = childSwitches.next();
                var switchJsonNode = switchesNode.get(childSwitch);
                var fetchedSwitch = getSwitchDeserialized(switchJsonNode.toString());
                switches.put(fetchedSwitch.getId(), fetchedSwitch);
            }
            ((EdgeRouter)router).setSwitches(switches);
        }
    }

    public static Router getRouterDeserialized(String jsonStr) throws IOException {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addDeserializer(Router.class, new RouterDeserializer());
        mapper.registerModule(module);
        var router = mapper.readValue(jsonStr, Router.class);
        return router;
    }

}
