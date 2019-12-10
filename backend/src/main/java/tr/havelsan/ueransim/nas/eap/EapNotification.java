package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapNotification extends Eap {
    public OctetString rawData;

    public EapNotification() {
    }

    public EapNotification(OctetString rawData) {
        this.rawData = rawData;
    }
}
