package dev.catalogue.topology.framework.adapters.output.h2.data;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import java.sql.Types;
import java.util.UUID;


public class UUIDtypeconverter implements Converter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UUID convertObjectValueToDataValue(Object objectValue, Session session) {
		// TODO Auto-generated method stub
		return (UUID) objectValue;
	}

	@Override
	public UUID convertDataValueToObjectValue(Object dataValue, Session session) {
		// TODO Auto-generated method stub
		 return (UUID) dataValue;
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void initialize(DatabaseMapping mapping, Session session) {
		// TODO Auto-generated method stub
		DatabaseField field = mapping.getField();
        field.setSqlType(Types.OTHER);
        field.setTypeName("java.util.UUID");
        field.setColumnDefinition("UUID");
	}

}
