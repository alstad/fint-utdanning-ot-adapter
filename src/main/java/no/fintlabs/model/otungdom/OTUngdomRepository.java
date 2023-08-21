package no.fintlabs.model.otungdom;

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

    private final OTUngdomService otUngdomService;

    public OTUngdomRepository(OTUngdomService otUngdomService) {
        this.otUngdomService = otUngdomService;
    }

    @Override
    public OTUngdomResource saveResources(OTUngdomResource tjenesteResource, RequestFintEvent requestFintEvent) {
        return null;
    }

    @Override
    public List<OTUngdomResource> getResources() {
        return otUngdomService.getOTUngdomResources();
    }

    @Override
    public List<OTUngdomResource> getUpdatedResources() {
        return new ArrayList<>();
    }
}