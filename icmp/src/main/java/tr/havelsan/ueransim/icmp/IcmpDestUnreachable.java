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
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IcmpDestUnreachable extends IcmpPacket {
    public Octet code;
    public Octet2 checksum;
    public OctetString ipData;

    static IcmpDestUnreachable decode(OctetInputStream stream) {
        var icmp = new IcmpDestUnreachable();
        icmp.code = stream.readOctet();
        icmp.checksum = stream.readOctet2();
        stream.readOctet4(); // 4-octet unused
        icmp.ipData = stream.readOctetString();
        return icmp;
    }

    @Override
    void encode(OctetOutputStream stream) {
        stream.writeOctet(3);
        stream.writeOctet(code);
        stream.writeOctet2(checksum);
        stream.writeOctet4(0);
        stream.writeOctetString(ipData);
    }
}
