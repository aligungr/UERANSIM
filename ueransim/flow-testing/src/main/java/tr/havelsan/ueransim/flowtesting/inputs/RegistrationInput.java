package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.sim.ue.UserLocationInformationNr;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RegistrationInput {
    public final long ranUeNgapId;
    public final Bit3 ngKSI;
    public final IESNssai[] requestNssai;
    public final IE5gsMobileIdentity mobileIdentity;
    public final int rrcEstablishmentCause;
    public final String imei;
    public final OctetString authenticationResponseParameter;
    public final EapAkaInput eapAkaInput;
    public final UserLocationInformationNr userLocationInformationNr;

    public RegistrationInput(long ranUeNgapId, Bit3 ngKSI, IESNssai[] requestNssai, IE5gsMobileIdentity mobileIdentity, int rrcEstablishmentCause, String imei, OctetString authenticationResponseParameter, EapAkaInput eapAkaInput, UserLocationInformationNr userLocationInformationNr) {
        this.ranUeNgapId = ranUeNgapId;
        this.ngKSI = ngKSI;
        this.requestNssai = requestNssai;
        this.mobileIdentity = mobileIdentity;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
        this.imei = imei;
        this.authenticationResponseParameter = authenticationResponseParameter;
        this.eapAkaInput = eapAkaInput;
        this.userLocationInformationNr = userLocationInformationNr;
    }
}