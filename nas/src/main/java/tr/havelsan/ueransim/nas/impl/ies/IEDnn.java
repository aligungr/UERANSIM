/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.nio.charset.StandardCharsets;

public class IEDnn extends InformationElement4 {
    public OctetString data;

    public IEDnn() {
    }

    public IEDnn(OctetString data) {
        this.data = data;
    }

    public IEDnn(String ascii) {
        var bytes = ascii.getBytes(StandardCharsets.US_ASCII);

        var octets = new Octet[bytes.length + 1];
        for (int i = 0; i < bytes.length; i++) {
            octets[i + 1] = new Octet(bytes[i]);
        }
        octets[0] = new Octet(bytes.length);

        this.data = new OctetString(octets);
    }

    @Override
    protected IEDnn decodeIE4(OctetInputStream stream, int length) {
        var res = new IEDnn();
        res.data = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(data);
    }
}
