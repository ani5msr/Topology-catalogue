package dev.catalogue.topology.framework.adapters.output.h2.mysql.data;

import jakarta.persistence.Embeddable;

@Embeddable
public enum ProtocolData {
    IPV4,
    IPV6;
} 
