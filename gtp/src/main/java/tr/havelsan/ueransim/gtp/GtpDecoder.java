/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.gtp;

import tr.havelsan.ueransim.gtp.ext.*;
import tr.havelsan.ueransim.gtp.pdusup.PduSessionInformation;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;

public class GtpDecoder {

    public static GtpMessage decode(byte[] data) {
        return decode(new OctetInputStream(data));
    }

    public static GtpMessage decode(OctetString data) {
        return decode(new OctetInputStream(data));
    }

    public static GtpMessage decode(OctetInputStream stream) {
        var res = new GtpMessage();

        int fistIndex = stream.currentIndex();

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

        res.msgType = stream.readOctet();
        int gtpLen = stream.readOctet2I();
        res.teid = stream.readOctet4();

        if (sequenceNumberPresent || nPduNumberPresent || nextExtensionHeaderPresent) {
            var seq = stream.readOctet2();
            var nPduNum = stream.readOctet();
            var nextExtHeaderType = stream.readOctetI();

            if (sequenceNumberPresent)
                res.seq = seq;
            if (nPduNumberPresent)
                res.nPduNum = nPduNum;
            if (nextExtensionHeaderPresent) {
                res.extHeaders = new ArrayList<>();

                while (nextExtHeaderType != 0) {
                    int len = stream.readOctetI(); // NOTE: len is actually 4 times length

                    GtpExtHeader header = null;

                    switch (nextExtHeaderType) {
                        case 0b01000000:
                            header = decodeUdpPortExtHeader(len, stream);
                            break;
                        case 0b10000010:
                            header = decodeLongPdcpPduNumberExtHeader(len, stream);
                            break;
                        case 0b10000100:
                            header = decodeNrRanContainerExtHeader(len, stream);
                            break;
                        case 0b10000101:
                            header = decodePduSessionContainerExtHeader(len, stream);
                            break;
                        case 0b11000000:
                            header = decodePdcpPduNumberExtHeader(len, stream);
                            break;
                        case 0b10000001: // Not used in gNB
                        case 0b10000011: // Not used in gNB
                            break;
                        default:
                            throw new ReservedOrInvalidValueException("GTP next extension header type", nextExtHeaderType);
                    }

                    if (header != null)
                        res.extHeaders.add(header);

                    nextExtHeaderType = stream.readOctetI();
                }
            }
        }

        int read = stream.currentIndex() - fistIndex;
        int rem = gtpLen - (read - 8);
        res.payload = stream.readOctetString(rem);

        return res;
    }

    private static UdpPortExtHeader decodeUdpPortExtHeader(int len, OctetInputStream stream) {
        if (len != 1)
            throw new DecodingException("length must be 1 for UdpPortExtHeader");

        var res = new UdpPortExtHeader();
        res.port = stream.readOctet2();
        return res;
    }

    private static LongPdcpPduNumberExtHeader decodeLongPdcpPduNumberExtHeader(int len, OctetInputStream stream) {
        if (len != 2)
            throw new DecodingException("length must be 2 for LongPdcpPduNumberExtHeader");

        int num = stream.readOctet().getBitRangeI(0, 1);
        num <<= 8;
        num |= stream.readOctetI();
        num <<= 8;
        num |= stream.readOctetI();

        var res = new LongPdcpPduNumberExtHeader();
        res.pdcpPduNumber = num;
        return res;
    }

    private static NrRanContainerExtHeader decodeNrRanContainerExtHeader(int len, OctetInputStream stream) {
        // obtain actual length in octets. (but not used)
        len = 4 * len - 2;

        // TODO: See 38.425
        throw new NotImplementedException("NrRanContainerExtHeader not implemented yet");
    }

    private static PduSessionContainerExtHeader decodePduSessionContainerExtHeader(int len, OctetInputStream stream) {
        // obtain actual length in octets. (but not used)
        len = 4 * len - 2;

        var res = new PduSessionContainerExtHeader();
        res.pduSessionInformation = PduSessionInformation.decode(stream);
        return res;
    }

    private static PdcpPduNumberExtHeader decodePdcpPduNumberExtHeader(int len, OctetInputStream stream) {
        if (len != 1)
            throw new DecodingException("length must be 1 for PdcpPduNumberExtHeader");

        var res = new PdcpPduNumberExtHeader();
        res.pdcpPduNumber = stream.readOctet2();
        return res;
    }
}
