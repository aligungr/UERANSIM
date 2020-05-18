package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class RegistrationInput {
    public final Bit3 ngKSI;
    public final IESNssai[] requestNssai;
    public final int rrcEstablishmentCause;

    public RegistrationInput(Bit3 ngKSI, IESNssai[] requestNssai, int rrcEstablishmentCause) {
        this.ngKSI = ngKSI;
        this.requestNssai = requestNssai;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
    }
}