package dev.catalogue.topology.bootstrap;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.*;
import io.quarkus.runtime.annotations.RegisterForReflection;


@RegisterForReflection(targets = {
        CoreRouter.class,
        EdgeRouter.class,
        Switch.class,
        ID.class,
        IP.class,
        Location.class,
        Model.class,
        Network.class,
        Protocol.class,
        Routertype.class,
        Switchtype.class,
        Vendor.class,
})
public class ReflectionConfiguration {

}
