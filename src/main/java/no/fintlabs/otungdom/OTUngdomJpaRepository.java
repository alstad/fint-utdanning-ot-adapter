package no.fintlabs.otungdom;

import no.fint.model.resource.utdanning.ot.OTUngdomResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OTUngdomJpaRepository extends JpaRepository<OTUngdomEntity, String> {

    @Query("SELECT o.resource FROM otungdom o")
    List<OTUngdomResource> findAllResources();

}

