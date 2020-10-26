package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class IwDownlinkData {
    public final OctetString ipPacket;

    public IwDownlinkData(OctetString ipPacket) {
        this.ipPacket = ipPacket;
    }
}
