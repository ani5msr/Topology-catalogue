package dev.catalogue.topology.framework.adapters.output.mysql;

import dev.catalogue.topology.framework.adapters.output.mysql.mappers.RouterMapper;
import dev.catalogue.topology.framework.adapters.output.mysql.respository.*;
import dev.catalogue.topology.application.ports.output.*;
import dev.catalogue.topology.domain.entity.Router;
import dev.catalogue.topology.domain.valueobj.ID;
import io.quarkus.hibernate.reactive.panache.Panache;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class RouterSQLAdapter implements RouterOutputPort {
        @Inject
        RouterRepository RouterRepo;
        @Override
        public Router retrieveRouter(ID id) {
            var routerData = RouterRepo.findById(id.getUuid()).subscribe().asCompletionStage().join();
            return RouterMapper.routerDataToDomain(routerData);
        }
        @Override
        public Router removeRouter(ID id) {
            var removed = RouterRepo.deleteById(id.getUuid()).subscribe().asCompletionStage().join();
            if(!removed){
                throw new InternalError();
            }
            return null;
        }
        @Override
        public Router persistRouter(Router router) {
            var routerData = RouterMapper.routerDomainToData(router);
            Panache.withTransaction(()->RouterRepo.persist(routerData));
            return router;
        }

}
