package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapIdentity extends Eap {
    public OctetString rawData;

    public EapIdentity() {
    }

    public EapIdentity(OctetString rawData) {
        this.rawData = rawData;
    }
}
