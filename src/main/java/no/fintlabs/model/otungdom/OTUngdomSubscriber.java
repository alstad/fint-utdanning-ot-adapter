package no.fintlabs.model.otungdom;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.utdanning.ot.OTUngdomResource;
import no.fintlabs.adapter.config.AdapterProperties;
import no.fintlabs.adapter.datasync.ResourceSubscriber;
import no.fintlabs.adapter.models.AdapterCapability;
import no.fintlabs.adapter.models.SyncPageEntry;
import no.fintlabs.adapter.validator.ValidatorService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class OTUngdomSubscriber extends ResourceSubscriber<OTUngdomResource, OTUngdomPublisher> {

    protected OTUngdomSubscriber(WebClient webClient, AdapterProperties props, OTUngdomPublisher publisher, ValidatorService<OTUngdomResource> validatorService) {
        super(webClient, props, publisher, validatorService);
    }

    @Override
    protected AdapterCapability getCapability() {
        return adapterProperties.getCapabilities().get("otungdom");
    }

    @Override
    protected SyncPageEntry<OTUngdomResource> createSyncPageEntry(OTUngdomResource resource) {
        return SyncPageEntry.of(resource.getSystemId().getIdentifikatorverdi(), resource);
    }
}
