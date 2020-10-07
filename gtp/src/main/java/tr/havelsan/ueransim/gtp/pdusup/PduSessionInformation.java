package tr.havelsan.ueransim.gtp.pdusup;

import org.apache.commons.net.ntp.TimeStamp;
import tr.havelsan.ueransim.core.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit6;

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
            res.qmp = octet.getBitB(3);

            octet = stream.readOctet();

            res.qfi = new Bit6(octet.getBitRangeI(0, 5));
            res.rqi = octet.getBitB(6);
            var ppp = octet.getBitB(7);

            if (ppp) {
                octet = stream.readOctet();
                res.ppi = octet.getBitRangeI(5, 7);
            }

            if (res.qmp) {
                var sendingTimeStamp0 = stream.readOctet4L();
                var sendingTimeStamp1 = stream.readOctet4L();

                res.dlSendingTs = TimeStamp.getNtpTime(sendingTimeStamp0 << 32 | sendingTimeStamp1);
            }

            if (snp) {
                res.dlQfiSeq = stream.readOctet3();
            }

            // Consuming padding if any. See 5.5.3.5
            var read = stream.currentIndex() - startIndex;
            if ((read - 2) % 4 != 0) {
                int padding = 4 - ((read - 2) % 4);
                stream.readOctetString(padding);
            }

            return res;
        } else {
            var res = new UlPduSessionInformation();

            var snp = octet.getBitB(0);
            var ulDelay = octet.getBitB(1);
            var dlDelay = octet.getBitB(2);
            res.qmp = octet.getBitB(3);

            res.qfi = new Bit6(stream.readOctet().getBitRangeI(0, 5));

            if (res.qmp) {
                var first = stream.readOctet4L();
                var second = stream.readOctet4L();
                res.dlSendingTsRepeated = TimeStamp.getNtpTime(first << 32 | second);

                first = stream.readOctet4L();
                second = stream.readOctet4L();
                res.dlReceivedTs = TimeStamp.getNtpTime(first << 32 | second);

                first = stream.readOctet4L();
                second = stream.readOctet4L();
                res.ulSendingTs = TimeStamp.getNtpTime(first << 32 | second);
            }

            if (dlDelay) {
                res.dlDelayResult = stream.readOctet4();
            }

            if (ulDelay) {
                res.ulDelayResult = stream.readOctet4();
            }

            if (snp) {
                res.ulQfiSeq = stream.readOctet3();
            }

            // Consuming padding if any. See 5.5.3.5
            var read = stream.currentIndex() - startIndex;
            if ((read - 2) % 4 != 0) {
                int padding = 4 - ((read - 2) % 4);
                stream.readOctetString(padding);
            }

            return res;
        }
    }

    public static void encode(PduSessionInformation pdu, OctetOutputStream stream) {
        if (pdu.pduType != 0 && pdu.pduType != 1)
            throw new DecodingException("invalid PDU Type for PduSessionInformation");

        if (pdu.pduType == 0) {

        } else {

        }
    }
}
