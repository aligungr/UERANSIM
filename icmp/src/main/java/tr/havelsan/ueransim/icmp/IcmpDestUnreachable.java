package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IcmpDestUnreachable extends IcmpPacket {
    public Octet type;
    public Octet code;
    public Octet2 checksum;
    public OctetString ipData;

    static IcmpDestUnreachable decode(OctetInputStream stream) {
        var icmp = new IcmpDestUnreachable();
        icmp.type = stream.readOctet();
        icmp.code = stream.readOctet();
        icmp.checksum = stream.readOctet2();
        stream.readOctet4(); // 4-octet unused
        icmp.ipData = stream.readOctetString();
        return icmp;
    }

    @Override
    void encode(OctetOutputStream stream) {
        stream.writeOctet(type);
        stream.writeOctet(code);
        stream.writeOctet2(checksum);
        stream.writeOctet4(0);
        stream.writeOctetString(ipData);
    }
}
