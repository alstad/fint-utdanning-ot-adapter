package no.fintlabs

import no.fint.model.felles.kompleksedatatyper.Identifikator
import no.fint.model.resource.utdanning.ot.OTUngdomResource
import no.fintlabs.adapter.models.RequestFintEvent
import no.fintlabs.otungdom.OTUngdomJpaRepository
import no.fintlabs.otungdom.OTUngdomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
class OTUngdomRepositorySpec extends Specification {

    @Autowired
    OTUngdomJpaRepository otungdomJpaRepository

    def setup() {
        // Clean up the repository before each test
        otungdomJpaRepository.deleteAll()
    }

    def "test saveResources method"() {
        given: "an OTUngdomResource and a RequestFintEvent"
        OTUngdomResource oTUngdomResource = new OTUngdomResource()
        RequestFintEvent requestFintEvent = new RequestFintEvent()

        requestFintEvent.setOrgId("fintlabs.no")

        Identifikator systemId = new Identifikator()
        systemId.setIdentifikatorverdi("12345")
        oTUngdomResource.setSystemId(systemId)

        and: "an OTUngdomRepository instance"
        def oTUngdomRepository = new OTUngdomRepository(otungdomJpaRepository)

        when: "saveResources is called"
        def savedResource = oTUngdomRepository.saveResources(oTUngdomResource, requestFintEvent)

        then: "the resulting OTUngdomResource should have the same values"
        savedResource == oTUngdomResource

        and: "an OTUngdomEntity should be persisted with the same values"
        def entity = otungdomJpaRepository.findById("12345").orElse(null)
        entity != null
        entity.resource == oTUngdomResource
        entity.orgId == "fintlabs.no"
    }

}
