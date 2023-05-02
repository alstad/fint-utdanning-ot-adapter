package no.fintlabs.otungdom;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import no.fint.model.resource.utdanning.ot.OTUngdomResource;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDefs({@TypeDef(name = "json", typeClass = JsonType.class)})
@Entity(name = "otungdom")
public class OTUngdomEntity {

    @Id
    private String id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private OTUngdomResource resource;

    private String orgId;

    private LocalDateTime lastModifiedDate;

    public static OTUngdomEntity toEntity(OTUngdomResource resource, String orgId) {
        return OTUngdomEntity
                .builder()
                .id(resource.getSystemId().getIdentifikatorverdi())
                .resource(resource)
                .orgId(orgId)
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
}
