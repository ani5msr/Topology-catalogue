package dev.catalogue.topology.framework.adapters.input.rest.converters;

import javax.inject.Singleton;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import dev.catalogue.topology.domain.valueobj.*;
@Provider
@Singleton
public class IDConverterProvider implements ParamConverterProvider {
	 @SuppressWarnings("unchecked")
	 @Override
	    public <T> ParamConverter<T> getConverter(Class<T> rawType,
	                                              Type genericType,
	                                              Annotation[] annotations) {
	        if (rawType.isAssignableFrom(ID.class)) {
	            return (ParamConverter<T>) new IDConverter();
	        }
	        return null;
	    }
}
