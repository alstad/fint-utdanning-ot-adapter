package no.fintlabs.restutil.model;

import lombok.Data;

@Data
public class OTUngdommer {

    private PersonData person;
    private OTData otData;
    private Elevkurs elevkurs;

}
