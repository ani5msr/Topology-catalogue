package dev.catalogue.topology.domain.specification;

import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.entity.*;
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
}
