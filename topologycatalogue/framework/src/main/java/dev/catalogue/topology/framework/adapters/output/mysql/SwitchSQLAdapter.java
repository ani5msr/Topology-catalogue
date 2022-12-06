package dev.catalogue.topology.framework.adapters.output.mysql;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dev.catalogue.topology.application.ports.output.SwitchOutputPort;
import dev.catalogue.topology.domain.entity.Switch;
import dev.catalogue.topology.domain.valueobj.ID;
import dev.catalogue.topology.framework.adapters.output.mysql.mappers.RouterMapper;
import dev.catalogue.topology.framework.adapters.output.mysql.respository.SwitchRepository;
@ApplicationScoped
public class SwitchSQLAdapter implements SwitchOutputPort {
	@Inject
    SwitchRepository SwitchRepo;

    @Override
    public Switch retrieveSwitch(ID id) {
        var switchData = SwitchRepo.findById(id.getUuid()).subscribe().asCompletionStage().join();
        return RouterMapper.switchDataToDomain(switchData);
    }

}
