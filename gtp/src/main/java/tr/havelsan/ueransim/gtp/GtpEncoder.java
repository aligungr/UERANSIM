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
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;

public class GtpEncoder {

    public static byte[] encode(GtpMessage gtp) {
        var stream = new OctetOutputStream();
        encode(gtp, stream);
        return stream.toByteArray();
    }

    public static OctetString encodeS(GtpMessage gtp) {
        return new OctetString(encode(gtp));
    }

    public static void encode(GtpMessage gtp, OctetOutputStream outputStream) {
        var stream = new OctetOutputStream();

        boolean pn = gtp.nPduNum != null;
        boolean s = gtp.seq != null;
        boolean e = gtp.extHeaders != null && !gtp.extHeaders.isEmpty();

        boolean any = pn || s || e;

        var pt = 1;
        var version = 1;

        stream.writeOctet(new Octet()
                .setBit(0, pn)
                .setBit(1, s)
                .setBit(2, e)
                .setBit(4, pt)
                .setBitRange(5, 7, version)
                .intValue());

        stream.writeOctet(gtp.msgType);
        stream.writeOctet2(0); // Dummy length for now.
        stream.writeOctet4(gtp.teid);

        if (any) {
            stream.writeOctet2(gtp.seq == null ? 0 : gtp.seq.intValue());
            stream.writeOctet(gtp.nPduNum == null ? 0 : gtp.nPduNum.intValue());

            var extHeaders = gtp.extHeaders;
            if (extHeaders == null) extHeaders = new ArrayList<>();

            for (var header : extHeaders) {
                encodeExtensionHeader(header, stream);
            }

            stream.writeOctet(0); // no more extension headers.
        }

        stream.writeOctetString(gtp.payload);

        var data = stream.toByteArray();

        // assigning length field
        int length = data.length - 8;
        data[2] = (byte) (length >> 8 & 0xFF);
        data[3] = (byte) (length & 0xFF);

        outputStream.writeOctets(data);
    }

    private static void encodeExtensionHeader(GtpExtHeader header, OctetOutputStream stream) {
        if (header instanceof PduSessionContainerExtHeader) {
            stream.writeOctet(0b10000101);

            var inner = new OctetOutputStream();

            var pduSession = ((PduSessionContainerExtHeader) header).pduSessionInformation;
            PduSessionInformation.encode(pduSession, inner);

            stream.writeOctet((inner.length() + 2) / 4);
            stream.writeStream(inner);
        } else if (header instanceof PdcpPduNumberExtHeader) {
            stream.writeOctet(0b11000000);
            stream.writeOctet(1);
            stream.writeOctet2(((PdcpPduNumberExtHeader) header).pdcpPduNumber);
        } else if (header instanceof UdpPortExtHeader) {
            stream.writeOctet(0b01000000);
            stream.writeOctet(1);
            stream.writeOctet2(((UdpPortExtHeader) header).port);
        } else if (header instanceof LongPdcpPduNumberExtHeader) {
            var num = ((LongPdcpPduNumberExtHeader) header).pdcpPduNumber;

            stream.writeOctet(0b10000010);
            stream.writeOctet(2);
            stream.writeOctet(num >> 16 & 0b11);
            stream.writeOctet(num >> 8 & 0xFF);
            stream.writeOctet(num & 0xFF);
        } else if (header instanceof NrRanContainerExtHeader) {
            // TODO: See 38.425
            throw new NotImplementedException("NrRanContainerExtHeader not implemented yet");
        }
    }
}
