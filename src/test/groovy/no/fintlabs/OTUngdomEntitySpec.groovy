package no.fintlabs

import no.fint.model.felles.kompleksedatatyper.Identifikator
import no.fint.model.resource.utdanning.ot.OTUngdomResource
import no.fintlabs.otungdom.OTUngdomEntity
import spock.lang.Specification

class OTUngdomEntitySpec extends Specification {

    def "test toEntity method"() {
        given: "A BehandlingResource object"
        OTUngdomResource oTUngdomResource = new OTUngdomResource()

        Identifikator systemId = new Identifikator()
        systemId.setIdentifikatorverdi("12345")
        oTUngdomResource.setSystemId(systemId)

        oTUngdomResource.setSystemId(systemId)

        when: "Calling the toEntity method"
        OTUngdomEntity oTUngdomEntity = OTUngdomEntity.toEntity(oTUngdomResource, "fintlabs.no")

        then: "Properties of the Behandling object should match the properties of the BehandlingResource object"
        oTUngdomResource.systemId.identifikatorverdi == oTUngdomEntity.getResource().getSystemId().getIdentifikatorverdi()
    }

}
