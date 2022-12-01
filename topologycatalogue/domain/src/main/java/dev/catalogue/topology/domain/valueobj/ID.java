package dev.catalogue.topology.domain.valueobj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.UUID;
@Getter
@ToString
@EqualsAndHashCode
public class ID {
	private final UUID id;
	 private ID(UUID id){
	 this.id = id;
	 }
	 public static ID withId(String id){
	 return new ID(UUID.fromString(id));
	 }
	 public static ID withoutId(){
	 return new ID(UUID.randomUUID());
	 }
	public UUID getUuid() {
		// TODO Auto-generated method stub
		return id;
	}
	 

}
