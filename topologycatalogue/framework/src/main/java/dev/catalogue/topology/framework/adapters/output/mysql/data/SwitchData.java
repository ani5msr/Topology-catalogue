package dev.catalogue.topology.framework.adapters.output.mysql.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "switches")
public class SwitchData {

    @jakarta.persistence.ManyToOne
    private RouterData router;

    @jakarta.persistence.Id
    @jakarta.persistence.Column(name="switch_id", columnDefinition = "BINARY(16)")
    private UUID switchId;

    @jakarta.persistence.Column(name="router_id", columnDefinition = "BINARY(16)")
    private UUID routerId;

    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(name="switch_vendor")
    private VendorData switchVendor;

    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(name="switch_model")
    private ModelData switchModel;

    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(name = "switch_type")
    private SwitchtypeData switchType;

    @jakarta.persistence.OneToMany(cascade = jakarta.persistence.CascadeType.ALL, fetch = jakarta.persistence.FetchType.EAGER)
    @jakarta.persistence.JoinColumn(name="switch_id")
    private Set<NetworkData> networks;

    @jakarta.persistence.Embedded
    @jakarta.persistence.AttributeOverrides({
            @jakarta.persistence.AttributeOverride(
                    name = "address",
                    column = @jakarta.persistence.Column(
                            name = "switch_ip_address")),
            @jakarta.persistence.AttributeOverride(
                    name = "protocol",
                    column = @jakarta.persistence.Column(
                            name = "switch_ip_protocol")),
    })
    private IpData ip;

    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name="location_id")
    private LocationData switchLocation;
}