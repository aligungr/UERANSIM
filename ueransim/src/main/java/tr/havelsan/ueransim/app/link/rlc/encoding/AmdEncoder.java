package tr.havelsan.ueransim.app.link.rlc.encoding;

import tr.havelsan.ueransim.app.link.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.app.link.rlc.utils.ESegmentInfo;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class AmdEncoder {

    public static AmdPdu decode(OctetInputStream stream, boolean isShortSn) {
        var pdu = new AmdPdu();

        var octet = stream.readOctet();
        pdu.dc = octet.getBitB(7);
        pdu.p = octet.getBitB(6);
        pdu.si = ESegmentInfo.fromSi(octet.getBitRangeI(4, 5));
        pdu.sn = isShortSn ? octet.getBitRangeI(0, 3) : octet.getBitRangeI(0, 1);

        pdu.sn <<= 8;
        pdu.sn |= stream.readOctetI();
        if (!isShortSn) {
            pdu.sn <<= 8;
            pdu.sn |= stream.readOctetI();
        }

        if (pdu.si.requiresSo()) {
            pdu.so = stream.readOctet2I();
        }

        pdu.data = stream.readOctetString();
        return pdu;
    }

    public static void encode(OctetOutputStream stream, AmdPdu pdu, boolean isShortSn) {
        var octet = new Octet()
                .setBit(7, pdu.dc)
                .setBit(6, pdu.p)
                .setBitRange(4, 5, pdu.si.intValue());
        if (isShortSn) {
            octet = octet.setBitRange(0, 3, pdu.sn >> 8);
        } else {
            octet = octet.setBitRange(0, 2, pdu.sn >> 16);
        }

        stream.writeOctet(octet.intValue());

        if (!isShortSn) stream.writeOctet((pdu.sn >> 8) & 0xFF);
        stream.writeOctet(pdu.sn & 0xFF);

        if (pdu.si.requiresSo()) {
            stream.writeOctet2(pdu.so);
        }

        stream.writeOctetString(pdu.data);
    }
}
