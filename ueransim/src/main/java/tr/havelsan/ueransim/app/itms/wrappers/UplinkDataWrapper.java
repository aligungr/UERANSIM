package tr.havelsan.ueransim.app.itms.wrappers;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class UplinkDataWrapper {
    public final OctetString ipData;

    public UplinkDataWrapper(OctetString ipData) {
        this.ipData = ipData;
    }
}
