package tr.havelsan.ueransim.app.itms.wrappers;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class IwUplinkData {
    public final OctetString ipData;

    public IwUplinkData(OctetString ipData) {
        this.ipData = ipData;
    }
}
