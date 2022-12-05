package dev.catalogue.topology.framework.adapters.output.h2.mysql.data;

import jakarta.persistence.*;

@Embeddable
public enum SwitchtypeData {
	LAYER2,
	LAYER3;
}
