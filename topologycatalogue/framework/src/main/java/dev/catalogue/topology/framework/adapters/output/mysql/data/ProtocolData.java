package dev.catalogue.topology.framework.adapters.output.mysql.data;

import jakarta.persistence.Embeddable;

@Embeddable
public enum ProtocolData {
    IPV4,
    IPV6;
} 
