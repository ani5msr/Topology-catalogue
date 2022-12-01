package dev.catalogue.topology.framework.adapters.output.h2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import dev.catalogue.topology.framework.adapters.output.h2.mapper.*;
import dev.catalogue.topology.framework.adapters.output.h2.data.*;
import dev.catalogue.topology.application.ports.output.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.valueobj.ID;
public class RouterH2adapter implements RouterOutputPort {
	private static RouterH2adapter adapterinstance;
	@PersistenceContext
	private EntityManager em;
	private RouterH2adapter(){
    }
	@Override
	public Router retrieveRouter(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Router persistRouter(Router router) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
