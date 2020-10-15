package tr.havelsan.ueransim.app.itms.wrappers;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class DownlinkDataWrapper {
    public final OctetString ipPacket;

    public DownlinkDataWrapper(OctetString ipPacket) {
        this.ipPacket = ipPacket;
    }
}
