/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IcmpRawPacket extends IcmpPacket {
    public final OctetString rawData;

    public IcmpRawPacket(OctetString rawData) {
        this.rawData = rawData;
    }

    @Override
    void encode(OctetOutputStream stream) {
        stream.writeOctetString(rawData);
    }
}
