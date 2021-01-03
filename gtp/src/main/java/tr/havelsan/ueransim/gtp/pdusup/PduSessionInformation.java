/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.gtp.pdusup;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.octets.Octet;

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
                res.dlSendingTs = stream.readOctet8L();
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
                res.dlSendingTsRepeated = stream.readOctet8L();
                res.dlReceivedTs = stream.readOctet8L();
                res.ulSendingTs = stream.readOctet8L();
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

        int initialLength = stream.length();

        if (pdu.pduType == 0) {
            var dl = (DlPduSessionInformation) pdu;

            var snp = dl.dlQfiSeq != null;
            stream.writeOctet(new Octet()
                    .setBit(2, snp)
                    .setBit(4, dl.qmp)
                    .setBitRange(4, 7, pdu.pduType)
                    .intValue());

            stream.writeOctet(new Octet()
                    .setBitRange(0, 5, dl.qfi.intValue())
                    .setBit(6, dl.rqi)
                    .setBit(7, dl.ppi != null)
                    .intValue());

            if (dl.ppi != null) {
                stream.writeOctet(new Octet()
                        .setBitRange(5, 7, dl.ppi)
                        .intValue());
            }

            if (dl.qmp) {
                stream.writeOctet8(dl.dlSendingTs == null ? 0 : dl.dlSendingTs);
            }

            if (snp) {
                stream.writeOctet3(dl.dlQfiSeq);
            }

        } else {
            var ul = (UlPduSessionInformation) pdu;

            var snp = ul.ulQfiSeq != null;
            stream.writeOctet(new Octet()
                    .setBit(0, snp)
                    .setBit(1, ul.ulDelayResult != null)
                    .setBit(2, ul.dlDelayResult != null)
                    .setBit(3, ul.qmp)
                    .setBitRange(4, 7, pdu.pduType)
                    .intValue());

            stream.writeOctet(new Octet()
                    .setBitRange(0, 5, ul.qfi.intValue())
                    .intValue());

            if (ul.qmp) {
                stream.writeOctet8(ul.dlSendingTsRepeated == null ? 0 : ul.dlSendingTsRepeated);
                stream.writeOctet8(ul.dlReceivedTs == null ? 0 : ul.dlReceivedTs);
                stream.writeOctet8(ul.ulSendingTs == null ? 0 : ul.ulSendingTs);
            }

            if (ul.dlDelayResult != null) {
                stream.writeOctet4(ul.dlDelayResult);
            }

            if (ul.ulDelayResult != null) {
                stream.writeOctet4(ul.ulDelayResult);
            }

            if (snp) {
                stream.writeOctet3(ul.ulQfiSeq);
            }
        }

        // Adjusting padding. See 5.5.3.5
        int written = stream.length() - initialLength;
        if ((written - 2) % 4 != 0) {
            int padding = 4 - ((written - 2) % 4);
            stream.writeOctetPadding(padding);
        }
    }
}
