package dev.catalogue.topology.framework.adapters.input.rest.converters;
import javax.ws.rs.ext.ParamConverter;
import dev.catalogue.topology.domain.valueobj.*;
public class IDConverter implements ParamConverter<ID>{
	 @Override
	    public ID fromString(String value){
	       return ID.withId(value);
	    }

	    @Override
	    public String toString(ID id) {
	        return id.getUuid().toString();
	    }

}
