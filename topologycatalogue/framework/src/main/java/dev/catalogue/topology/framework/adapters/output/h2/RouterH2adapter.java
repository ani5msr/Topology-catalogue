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
		setUpH2Database();
    }
	@Override
	public Router retrieveRouter(ID id) {
		 var routerData = em.getReference(RouterData.class, id.getUuid());
	     return RouterMapper.routerDataToDomain(routerData);
	}

	@Override
	public Router persistRouter(Router router) {
		 var routerData = em.getReference(RouterData.class, ID.getUuid());
	     em.remove(routerData);
	     return null;
	}

    @Override
    public Router removeRouter(ID id) {
        var routerData = em.getReference(RouterData.class, id.getUuid());
        em.remove(routerData);
        return null;
    }
    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("catalogue");
        EntityManager em =
                entityManagerFactory.createEntityManager();
        this.em = em;
    }
    public static RouterH2adapter getInstance() {
        if (adapterinstance == null) {
            adapterinstance = new RouterH2adapter();
        }
        return adapterinstance;
    }
	
}
