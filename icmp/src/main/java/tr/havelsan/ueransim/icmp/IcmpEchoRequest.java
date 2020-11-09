package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IcmpEchoRequest extends IcmpPacket {
    public Octet type;
    public Octet code;
    public Octet2 checksum;
    public Octet2 identifier;
    public Octet2 sequenceNumber;
    public OctetString data;

    static IcmpEchoRequest decode(OctetInputStream stream) {
        var icmp = new IcmpEchoRequest();
        icmp.type = stream.readOctet();
        icmp.code = stream.readOctet();
        icmp.checksum = stream.readOctet2();
        icmp.identifier = stream.readOctet2();
        icmp.sequenceNumber = stream.readOctet2();
        icmp.data = stream.readOctetString();
        return icmp;
    }

    @Override
    void encode(OctetOutputStream stream) {
        stream.writeOctet(type);
        stream.writeOctet(code);
        stream.writeOctet2(checksum);
        stream.writeOctet2(identifier);
        stream.writeOctet2(identifier);
        stream.writeOctet2(sequenceNumber);
        stream.writeOctetString(data);
    }
}
