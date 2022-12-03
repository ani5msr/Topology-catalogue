package dev.catalogue.topology.framework.adapters.output.h2;

import dev.catalogue.topology.application.ports.output.*;
import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.ID;
import dev.catalogue.topology.framework.adapters.output.h2.mapper.*;
import dev.catalogue.topology.framework.adapters.output.h2.data.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
public class SwitchH2adapter implements SwitchOutputPort {
	private static SwitchH2adapter adapterinstance;
	@PersistenceContext
    private EntityManager em;
	
    public SwitchH2adapter(){
	        setUpH2Database();
	    }
	@Override
	public Switch retrieveSwitch(ID id) {
		  var switchData = em.getReference(SwitchData.class, id.getUuid());
	       return RouterMapper.switchDataToDomain(switchData);
	}
	 private void setUpH2Database() {
	        EntityManagerFactory entityManagerFactory =
	                Persistence.createEntityManagerFactory("catalogue");
	        EntityManager em =
	                entityManagerFactory.createEntityManager();
	        this.em = em;
	    }
	 public static SwitchH2adapter getInstance() {
	        if (adapterinstance == null) {
	            adapterinstance = new SwitchH2adapter();
	        }
	        return adapterinstance;
	    }

}
