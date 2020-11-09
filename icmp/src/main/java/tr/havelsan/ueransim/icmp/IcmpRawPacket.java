package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IcmpRawPacket extends IcmpPacket {
    public final OctetString rawData;

    public IcmpRawPacket(OctetString rawData) {
        this.rawData = rawData;
    }

    @Override
    void encode(OctetOutputStream stream) {
        stream.writeOctetString(rawData);
    }
}
