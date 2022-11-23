package dev.catalogue.topology.domain.specification;
import dev.catalogue.topology.domain.specification.common.*;
import dev.catalogue.topology.domain.entity.*;
import dev.catalogue.topology.domain.exception.*;
public class SameIPSpecification extends AbstractSpecification<Equipment> {
		private Equipment equipment;

		public Equipment getEquipment() {
			return equipment;
		}

		public void setEquipment(Equipment equipment) {
			this.equipment = equipment;
		}
		public SameIPSpecification(Equipment equipment) {
			this.equipment = equipment;
		}
		@Override
		public boolean isSatisfiedBy(Equipment anyEquipment) {
	        return !equipment.getIp().equals(anyEquipment.getIp());
	    }
	    @Override
		public void check(Equipment equipment) {
		        if(!isSatisfiedBy(equipment))
		            throw new GenericException("It's not possible to attach routers with the same IP");
		}
}
