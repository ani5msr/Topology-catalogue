package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.exception.GenericException;
public class SameCountrySpecification extends AbstractSpecification<Equipment> {
	 private Equipment equipment;

	 public SameCountrySpecification(Equipment equipment){
	        this.equipment = equipment;
	    }
	 @Override
	 public boolean isSatisfiedBy(Equipment anyEquipment) {
	        if(anyEquipment instanceof CoreRouter) {
	            return true;
	        } else if (anyEquipment != null && this.equipment != null) {
	            return this.equipment.getLocation().getCountry().
	                    equals(anyEquipment.getLocation().getCountry());
	        } else{
	            return false;
	        }
	    }
	@Override
	public void check(Equipment t) throws GenericException {
		if(!isSatisfiedBy(equipment))
            throw new GenericException("The equipments should be in the same country");		
	}
}
