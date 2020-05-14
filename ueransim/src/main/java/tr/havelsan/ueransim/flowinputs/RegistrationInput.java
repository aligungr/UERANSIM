package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class RegistrationInput {
    public final long ranUeNgapId;
    public final Bit3 ngKSI;
    public final IESNssai[] requestNssai;
    public final int rrcEstablishmentCause;
    public final UserLocationInformationNr userLocationInformationNr;

    public RegistrationInput(long ranUeNgapId, Bit3 ngKSI, IESNssai[] requestNssai, int rrcEstablishmentCause, UserLocationInformationNr userLocationInformationNr) {
        this.ranUeNgapId = ranUeNgapId;
        this.ngKSI = ngKSI;
        this.requestNssai = requestNssai;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
        this.userLocationInformationNr = userLocationInformationNr;
    }
}