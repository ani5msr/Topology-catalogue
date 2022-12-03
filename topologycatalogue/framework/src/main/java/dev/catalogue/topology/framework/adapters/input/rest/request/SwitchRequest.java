package dev.catalogue.topology.framework.adapters.input.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.catalogue.topology.domain.valueobj.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SwitchRequest {
	 @JsonProperty
	    private Vendor vendor;

	    @JsonProperty
	    private Model model;

	    @JsonProperty
	    private String ip;

	    @JsonProperty
	    private Location location;

	    @JsonProperty
	    private Switchtype switchType;
}
