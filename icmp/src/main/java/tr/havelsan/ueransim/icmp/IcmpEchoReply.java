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

public class IcmpEchoReply extends IcmpPacket {
    public Octet code;
    public Octet2 checksum;
    public Octet2 identifier;
    public Octet2 sequenceNumber;
    public OctetString data;

    static IcmpEchoReply decode(OctetInputStream stream) {
        var icmp = new IcmpEchoReply();
        icmp.code = stream.readOctet();
        icmp.checksum = stream.readOctet2();
        icmp.identifier = stream.readOctet2();
        icmp.sequenceNumber = stream.readOctet2();
        icmp.data = stream.readOctetString();
        return icmp;
    }

    @Override
    void encode(OctetOutputStream stream) {
        stream.writeOctet(0);
        stream.writeOctet(code);
        stream.writeOctet2(checksum);
        stream.writeOctet2(identifier);
        stream.writeOctet2(sequenceNumber);
        stream.writeOctetString(data);
    }
}
