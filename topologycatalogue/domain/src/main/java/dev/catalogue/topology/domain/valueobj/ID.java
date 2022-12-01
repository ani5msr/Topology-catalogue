package dev.catalogue.topology.domain.valueobj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.UUID;
@Getter
@ToString
@EqualsAndHashCode
public class ID {
	@Getter
	 private static UUID uuid;
	 private ID(UUID id){
	 this.uuid = id;
	 }
	 public static ID withId(String id){
	 return new ID(UUID.fromString(id));
	 }
	 public static ID withoutId(){
	 return new ID(UUID.randomUUID());
	 }
	 

}
