package dev.catalogue.topology.framework.adapters.output.h2.data;

import jakarta.persistence.*;

@Embeddable
public enum RoutertypeData {
	EDGE,
	CORE
}
