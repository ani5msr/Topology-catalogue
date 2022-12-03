package dev.catalogue.topology.application.status;

import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.entity.factory.*;
public class RouterInfo {
	public String getRouterStatus () {
        var router = Routerfactory.getRouter(
                ID.withoutId(),
                Vendor.CISCO,
                Model.ABC0004,
                IP.fromAddress("55.0.0.1"),
                null,
                Routertype.Core);
        return "Router with "+router.getIp()+" is alive!";
    }
}
