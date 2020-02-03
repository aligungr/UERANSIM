package tr.havelsan.ueransim.parameterised;

import tr.havelsan.ueransim.nas.impl.ies.IE5gsMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RegistrationInput {
    public long ranUeNgapId;

    public Bit3 ngKSI;

    public IESNssai[] requestNssai;

    public IE5gsMobileIdentity mobileIdentity;

    public int rrcEstablishmentCause;

    public String imei;

    public OctetString authenticationResponseParameter;

    public EapAkaInputs eapAkaInputs;

    public RegistrationInput(long ranUeNgapId, Bit3 ngKSI, IESNssai[] requestNssai, IE5gsMobileIdentity mobileIdentity, int rrcEstablishmentCause, String imei, OctetString authenticationResponseParameter, EapAkaInputs eapAkaInputs) {
        this.ranUeNgapId = ranUeNgapId;
        this.ngKSI = ngKSI;
        this.requestNssai = requestNssai;
        this.mobileIdentity = mobileIdentity;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
        this.imei = imei;
        this.authenticationResponseParameter = authenticationResponseParameter;
        this.eapAkaInputs = eapAkaInputs;
    }
}