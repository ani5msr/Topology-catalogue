package dev.catalogue.topology.framework.adapters.output.mysql.respository;

import io.quarkus.hibernate.reactive.panache.*;

import dev.catalogue.topology.framework.adapters.output.mysql.data.RouterData;
import dev.catalogue.topology.framework.adapters.output.mysql.data.SwitchData;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;
import jakarta.persistence.*;
public class SwitchRepository implements PanacheRepositoryBase<SwitchData,UUID>{

}
