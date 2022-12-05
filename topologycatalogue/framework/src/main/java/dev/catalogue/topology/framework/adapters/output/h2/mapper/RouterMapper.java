package dev.catalogue.topology.framework.adapters.output.h2.mapper;

import dev.catalogue.topology.domain.valueobj.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.entity.factory.*;
import java.util.*;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.IpData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.LocationData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.ModelData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.NetworkData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.RouterData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.RoutertypeData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.SwitchData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.SwitchtypeData;
import dev.catalogue.topology.framework.adapters.output.h2.mysql.data.VendorData;
public class RouterMapper {
	public static Router routerDataToDomain(RouterData routerData){
        var router = Routerfactory.getRouter(
                ID.withId(routerData.getRouterId().toString()),
                Vendor.valueOf(routerData.getRouterVendor().toString()),
                Model.valueOf(routerData.getRouterModel().toString()),
                IP.fromAddress(routerData.getIp().getAddress()),
                locationDataToLocationDomain(routerData.getRouterLocation()),
                Routertype.valueOf(routerData.getRouterType().name()));
        if(routerData.getRouterType().equals(RoutertypeData.CORE)){
            var coreRouter = (CoreRouter) router;
            coreRouter.setRouters(getRoutersFromData(routerData.getRouters()));
            return coreRouter;
        } else {
            var edgeRouter = (EdgeRouter) router;
            edgeRouter.setSwitches(getSwitchesFromData(routerData.getSwitches()));
            return edgeRouter;
        }
    }
	public static Location locationDataToLocationDomain(LocationData locationData){
	        return Location.builder()
	                .address(locationData.getAddress())
	                .city(locationData.getCity())
	                .state(locationData.getState())
	                .zipCode(locationData.getZipcode())
	                .country(locationData.getCountry())
	                .latitude(locationData.getLatitude())
	                .longitude(locationData.getLongitude())
	                .build();
	    }
	private static Map<ID, Router> getRoutersFromData(Set<RouterData> routerDataList){
        Map<ID,Router> routerMap = new HashMap<>();
        for (RouterData routerData : routerDataList) {
            routerMap.put(
                    ID.withId(routerData.getRouterId().toString()),
                    routerDataToDomain(routerData));
        }
        return routerMap;
    }
	private static Map<ID, Switch> getSwitchesFromData(List<SwitchData> switchDataList){
	        Map<ID,Switch> switchMap = new HashMap<>();
	        for (SwitchData switchData : switchDataList) {
	            switchMap.put(
	                    ID.withId(switchData.getSwitchId().toString()),
	                    switchDataToDomain(switchData));
	        }
	        return switchMap;
	    }
	public static Switch switchDataToDomain(SwitchData switchData) {
	        return Switch.builder().
	                switchId(ID.withId(switchData.getSwitchId().toString())).
	                routerId(ID.withId(switchData.getRouterId().toString())).
	                vendor(Vendor.valueOf(switchData.getSwitchVendor().toString())).
	                model(Model.valueOf(switchData.getSwitchModel().toString())).
	                ip(IP.fromAddress(switchData.getIp().getAddress())).
	                location(locationDataToLocationDomain(switchData.getSwitchLocation())).
	                switchType(Switchtype.valueOf(switchData.getSwitchType().toString())).
	                switchNetworks(getNetworksFromData(switchData.getNetworks())).
	                build();
	    }
    private static List<Network> getNetworksFromData(Set<NetworkData> networkData){
	        List<Network> networks = new ArrayList<>();
	        networkData.forEach(data ->{
	            var network = new Network(
	                    IP.fromAddress(data.getIp().getAddress()),
	                    data.getName(),
	                    data.getCidr());
	            networks.add(network);
	        });
	        return networks;
	    }
    public static LocationData locationDomainToLocationData(Location location){
        return LocationData.builder()
                .address(location.getAddress())
                .city(location.getCity())
                .state(location.getState())
                .zipcode(location.getZipCode())
                .country(location.getCountry())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
    private static Set<NetworkData>  getNetworksFromDomain(List<Network> networks, UUID routerId){
        Set<NetworkData> networkDataList = new HashSet<>();
        if(networks!=null) {
            networks.forEach(network -> {
                var networkData = new NetworkData(
                        IpData.fromAddress(network.getNetworkAddress().getIpAddress()),
                        network.getNetworkName(),
                        network.getNetworkCidr()
                );
                networkDataList.add(networkData);
            });
        }
        return networkDataList;
    }

    public static SwitchData switchDomainToData(Switch aSwitch){
        return  SwitchData.builder().
                switchId(aSwitch.getId().getUuid()).
                routerId(aSwitch.getRouterId().getUuid()).
                switchVendor(VendorData.valueOf(aSwitch.getVendor().toString())).
                switchModel(ModelData.valueOf(aSwitch.getModel().toString())).
                ip(IpData.fromAddress(aSwitch.getIp().getIpAddress())).
                switchLocation(locationDomainToLocationData(aSwitch.getLocation())).
                switchType(SwitchtypeData.valueOf(aSwitch.getSwitchType().toString())).
                networks(getNetworksFromDomain(aSwitch.getSwitchNetworks(), aSwitch.getId().getUuid())).
                build();
    }
    public static RouterData routerDomainToData(Router router){
        var routerData = RouterData.builder().
                routerId(router.getId().getUuid()).
                routerVendor(VendorData.valueOf(router.getVendor().toString())).
                routerModel(ModelData.valueOf(router.getModel().toString())).
                ip(IpData.fromAddress(router.getIp().getIpAddress())).
                routerLocation(locationDomainToLocationData(router.getLocation())).
                routerType(RoutertypeData.valueOf(router.getRoutertype().toString())).
                build();
        if(router.getRoutertype().equals(Routertype.Core)) {
            var coreRouter = (CoreRouter) router;
            routerData.setRouters(getRoutersFromDomain(coreRouter.getRouters()));
        } else {
            var edgeRouter = (EdgeRouter) router;
            routerData.setSwitches(getSwitchesFromDomain(edgeRouter.getSwitches()));
        }
        return routerData;
    }
    private static Set<RouterData>  getRoutersFromDomain(Map<ID, Router> routers){
        Set<RouterData> routerDataList = new HashSet<>();
         routers.values().stream().forEach(router -> {
             var routerData = routerDomainToData(router);
             routerDataList.add(routerData);
         });
        return routerDataList;
    }
    private static List<SwitchData>  getSwitchesFromDomain(Map<ID, Switch> switches){
        List<SwitchData> switchDataList = new ArrayList<>();
        if(switches!=null) {
            switches.values().stream().forEach(aSwitch -> {
                switchDataList.add(switchDomainToData(aSwitch));
            });
        }
        return switchDataList;
    }  
}
