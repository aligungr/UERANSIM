/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IcmpEncoder {

    public static IcmpPacket decode(String hexData) {
        return decode(new OctetString(hexData));
    }

    public static IcmpPacket decode(byte[] data) {
        return decode(new OctetInputStream(data));
    }

    public static IcmpPacket decode(OctetString data) {
        return decode(new OctetInputStream(data));
    }

    public static IcmpPacket decode(OctetInputStream data) {
        switch (data.readOctetI()) {
            case 0:
                return IcmpEchoReply.decode(data);
            case 3:
                return IcmpDestUnreachable.decode(data);
            case 8:
                return IcmpEchoRequest.decode(data);
        }

        return new IcmpRawPacket(data.readOctetString());
    }

    public static void encode(IcmpPacket packet, OctetOutputStream stream) {
        packet.encode(stream);
    }

    public static OctetString encode(IcmpPacket packet) {
        var stream = new OctetOutputStream();
        encode(packet, stream);
        return stream.toOctetString();
    }
}
