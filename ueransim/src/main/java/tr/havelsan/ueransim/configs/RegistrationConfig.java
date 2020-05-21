package tr.havelsan.ueransim.configs;

import tr.havelsan.ueransim.nas.impl.ies.IESNssai;

public class RegistrationConfig {
    public final IESNssai[] requestNssai;
    public final int rrcEstablishmentCause;

    public RegistrationConfig(IESNssai[] requestNssai, int rrcEstablishmentCause) {
        this.requestNssai = requestNssai;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
    }
}