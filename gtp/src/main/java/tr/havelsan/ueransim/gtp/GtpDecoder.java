package tr.havelsan.ueransim.gtp;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.OctetInputStream;

// TODO: check for IP fragmentation and fitting IP packet inside of UDP carried GTP packet
//  (as well as hvgtptun side etc.)
public class GtpDecoder {

    public static GtpMessage decode(OctetInputStream stream) {
        var flags = stream.readOctet();

        int version = flags.getBitRangeI(5, 7);
        if (version != 1) {
            throw new NotImplementedException("GTP-U version not implemented");
        }

        int protocolType = flags.getBitI(4);
        if (protocolType != 1) {
            throw new NotImplementedException("GTP' not implemented");
        }

        boolean nextExtensionHeaderPresent = flags.getBitB(2);
        boolean sequenceNumberPresent = flags.getBitB(1);
        boolean nPduNumberPresent = flags.getBitB(0);

        return null;
    }
}
