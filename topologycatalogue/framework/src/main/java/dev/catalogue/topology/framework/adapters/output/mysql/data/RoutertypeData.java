package dev.catalogue.topology.framework.adapters.output.mysql.data;

import jakarta.persistence.*;

@Embeddable
public enum RoutertypeData {
	EDGE,
	CORE
}
