package no.fintlabs.model.otungdom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.Link;
import no.fint.model.resource.felles.PersonResource;
import no.fint.model.resource.utdanning.kodeverk.OtEnhetResource;
import no.fint.model.resource.utdanning.kodeverk.OtStatusResource;
import no.fint.model.resource.utdanning.ot.OtUngdomResource;
import no.fintlabs.restutil.RestUtil;
import no.fintlabs.restutil.model.OTUngdomData;
import no.fintlabs.restutil.model.RequestData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OtUngdomService {

    private final RestUtil restUtil;

    public OtUngdomService(RestUtil restUtil) {
        this.restUtil = restUtil;
    }

    public List<OtUngdomResource> getOtUngdomResources() {
        List<OtUngdomResource> otungdomResources = new ArrayList<>();
        RequestData requestData = restUtil.getRequestData().block();

        if (requestData != null) {
            requestData.getOtungdommer().forEach(otUngdomData -> {
                OtUngdomResource otungdomResource = createOtUngdomResource(otUngdomData);
                otungdomResources.add(otungdomResource);
            });
        }

        return otungdomResources;
    }

    @SneakyThrows
    private OtUngdomResource createOtUngdomResource(OTUngdomData otUngdomData) {
        OtUngdomResource otungdomResource = new OtUngdomResource();
        String fodselsNummer = otUngdomData.getPerson().getFodselsnummer();

        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi(fodselsNummer);
        otungdomResource.setSystemId(identifikator);

        otungdomResource.addPerson(Link.with(PersonResource.class, "fodselsnummer", fodselsNummer));
        otungdomResource.addEnhet(Link.with(OtEnhetResource.class, "utdanning/kodeverk/otenhet/systemid", otUngdomData.getOtData().getTilknytningnr()));
        otungdomResource.addStatus(Link.with(OtStatusResource.class, "utdanning/kodeverk/otstatus/systemid", otUngdomData.getOtData().getAktivitetskode()));
        otungdomResource.addSelf(Link.with(OtUngdomResource.class, "systemid", fodselsNummer));

        return otungdomResource;
    }

}
