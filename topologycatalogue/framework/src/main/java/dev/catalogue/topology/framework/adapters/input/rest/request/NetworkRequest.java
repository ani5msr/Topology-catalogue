package dev.catalogue.topology.framework.adapters.input.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NetworkRequest {
	 @JsonProperty
	 private String networkAddress;

	 @JsonProperty
	 private String networkName;

	 @JsonProperty
	 private int networkCidr;
}
