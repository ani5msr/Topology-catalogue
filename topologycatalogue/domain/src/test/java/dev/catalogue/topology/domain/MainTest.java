package dev.catalogue.topology.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.catalogue.topology.domain.valueobj.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.catalogue.topology.domain.specification.common.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class MainTest {
	 private Location createLocation(String country){
	        return Location.builder().
	                address("Test street").
	                city("Test City").
	                state("Test State").
	                country(country).
	                zipCode(00000).
	                latitude(10F).
	                longitude(-10F).
	                build();
	    }
}
