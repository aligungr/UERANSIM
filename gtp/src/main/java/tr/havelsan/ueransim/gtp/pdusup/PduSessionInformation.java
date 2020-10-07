package tr.havelsan.ueransim.gtp.pdusup;

import org.apache.commons.net.ntp.TimeStamp;
import tr.havelsan.ueransim.core.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.OctetInputStream;

// See 3GPP 38.415
public abstract class PduSessionInformation {

    public static final int PDU_TYPE_DL = 0;
    public static final int PDU_TYPE_UL = 1;

    public final int pduType;

    public PduSessionInformation(int pduType) {
        this.pduType = pduType;
    }

    public static PduSessionInformation decode(OctetInputStream stream) {
        int startIndex = stream.currentIndex();

        var octet = stream.readOctet();

        int type = octet.getBitRangeI(4, 7);
        if (type != 0 && type != 1)
            throw new DecodingException("invalid PDU Type for PduSessionInformation");

        if (type == 0) {
            var res = new DlPduSessionInformation();

            var snp = octet.getBitB(2);
            var qmp = octet.getBitB(3);

            octet = stream.readOctet();

            res.qfi = octet.getBitRangeI(0, 5);
            res.rqi = octet.getBitB(6);
            var ppp = octet.getBitB(7);

            if (ppp) {
                octet = stream.readOctet();
                res.ppi = octet.getBitRangeI(5, 7);
            }

            if (qmp) {
                var sendingTimeStamp0 = stream.readOctet4L();
                var sendingTimeStamp1 = stream.readOctet4L();

                res.sendingTs = TimeStamp.getNtpTime(sendingTimeStamp0 << 32 | sendingTimeStamp1);
            }

            if (snp) {
                res.qfiSeq = stream.readOctet3();
            }

            var read = stream.currentIndex() - startIndex;
            if ((read - 2) % 4 != 0) {
                int padding = 4 - ((read - 2) % 4);
                stream.readOctetString(padding);
            }

            return res;
        } else {
            var res = new UlPduSessionInformation();

            // TODO

            return res;
        }
    }
}
