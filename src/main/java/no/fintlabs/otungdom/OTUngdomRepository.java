package no.fintlabs.otungdom;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.utdanning.ot.OTUngdomResource;
import no.fintlabs.adapter.events.WriteableResourceRepository;
import no.fintlabs.adapter.models.RequestFintEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class OTUngdomRepository implements WriteableResourceRepository<OTUngdomResource> {

    private final OTUngdomJpaRepository otUngdomRepository;

    public OTUngdomRepository(OTUngdomJpaRepository otUngdomRepository) {
        this.otUngdomRepository = otUngdomRepository;
    }

    @Override
    public OTUngdomResource saveResources(OTUngdomResource tjenesteResource, RequestFintEvent requestFintEvent) {
        OTUngdomEntity entity = OTUngdomEntity.toEntity(tjenesteResource, requestFintEvent.getOrgId());
        return otUngdomRepository.save(entity).getResource();
    }

    @Override
    public List<OTUngdomResource> getResources() {
        return otUngdomRepository.findAllResources();
    }

    @Override
    public List<OTUngdomResource> getUpdatedResources() {
        return new ArrayList<>();
    }
}