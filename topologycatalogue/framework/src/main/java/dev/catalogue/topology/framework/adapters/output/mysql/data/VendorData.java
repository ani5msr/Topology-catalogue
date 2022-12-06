package dev.catalogue.topology.framework.adapters.output.mysql.data;

import jakarta.persistence.*;
@Embeddable
public enum VendorData {
	 CISCO,
	    NETGEAR,
	    HP,
	    TPLINK,
	    DLINK,
	    JUNIPER
}
