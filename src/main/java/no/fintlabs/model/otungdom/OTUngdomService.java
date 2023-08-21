package no.fintlabs.model.otungdom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.Link;
import no.fint.model.resource.felles.PersonResource;
import no.fint.model.resource.utdanning.kodeverk.OTEnhetResource;
import no.fint.model.resource.utdanning.kodeverk.OTStatusResource;
import no.fint.model.resource.utdanning.ot.OTUngdomResource;
import no.fintlabs.restutil.RestUtil;
import no.fintlabs.restutil.model.OTUngdomData;
import no.fintlabs.restutil.model.RequestData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OTUngdomService {

    private final RestUtil restUtil;

    public OTUngdomService(RestUtil restUtil) {
        this.restUtil = restUtil;
    }

    public List<OTUngdomResource> getOTUngdomResources() {
        List<OTUngdomResource> otungdomResources = new ArrayList<>();
        RequestData requestData = restUtil.getRequestData().block();

        if (requestData != null) {
            requestData.getOtungdommer().forEach(otUngdomData -> {
                OTUngdomResource otungdomResource = createOTUngdomResource(otUngdomData);
                otungdomResources.add(otungdomResource);
            });
        }

        return otungdomResources;
    }

    @SneakyThrows
    private OTUngdomResource createOTUngdomResource(OTUngdomData otUngdomData) {
        OTUngdomResource otungdomResource = new OTUngdomResource();
        String fodselsNummer = otUngdomData.getPerson().getFodselsnummer();

        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi(fodselsNummer);
        otungdomResource.setSystemId(identifikator);

        otungdomResource.addPerson(Link.with(PersonResource.class, "fodselsnummer", fodselsNummer));
        otungdomResource.addEnhet(Link.with(OTEnhetResource.class, "utdanning/kodeverk/otenhet/systemid", otUngdomData.getOtData().getTilknytningnr()));
        otungdomResource.addStatus(Link.with(OTStatusResource.class, "utdanning/kodeverk/otstatus/systemid", otUngdomData.getOtData().getAktivitetskode()));
        otungdomResource.addSelf(Link.with(OTUngdomResource.class, "systemid", fodselsNummer));

        return otungdomResource;
    }

}
