package tr.havelsan.ueransim.configs;

import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class RegistrationConfig {
    public final Bit3 ngKSI;
    public final IESNssai[] requestNssai;
    public final int rrcEstablishmentCause;

    public RegistrationConfig(Bit3 ngKSI, IESNssai[] requestNssai, int rrcEstablishmentCause) {
        this.ngKSI = ngKSI;
        this.requestNssai = requestNssai;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
    }
}