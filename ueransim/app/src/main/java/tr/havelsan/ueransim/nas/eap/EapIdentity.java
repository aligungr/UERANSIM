package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapIdentity extends Eap {
    public OctetString rawData;

    public EapIdentity(ECode code, Octet id) {
        super(code, id, EEapType.IDENTITY);
    }

    public EapIdentity(ECode code, Octet id, OctetString rawData) {
        super(code, id, EEapType.IDENTITY);
        this.rawData = rawData;
    }
}
