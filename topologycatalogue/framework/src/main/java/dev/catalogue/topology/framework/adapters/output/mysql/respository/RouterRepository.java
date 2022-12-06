package dev.catalogue.topology.framework.adapters.output.mysql.respository;

import io.quarkus.hibernate.reactive.panache.*;
import javax.enterprise.context.ApplicationScoped;

import dev.catalogue.topology.framework.adapters.output.mysql.data.RouterData;

import java.util.UUID;
import jakarta.persistence.*;
public class RouterRepository implements PanacheRepositoryBase<RouterData,UUID> {

}
