package tr.havelsan.ueransim.configs;

import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;

public class RegistrationConfig {
    public final IESNssai[] requestNssai;
    public final int rrcEstablishmentCause;

    public final boolean use5gGuti;
    public final IE5gGutiMobileIdentity gutiMobileIdentity;

    public RegistrationConfig(IESNssai[] requestNssai, int rrcEstablishmentCause, boolean use5gGuti, IE5gGutiMobileIdentity gutiMobileIdentity) {
        this.requestNssai = requestNssai;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
        this.use5gGuti = use5gGuti;
        this.gutiMobileIdentity = gutiMobileIdentity;
    }
}