/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEAbba extends InformationElement4 {
    // Currently only defined value is 0x0000 with length 2 (bytes)
    // (3GPP TS 33.501, 15.2.0)
    public OctetString contents;

    public IEAbba() {
    }

    public IEAbba(OctetString contents) {
        this.contents = contents;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var abba = new IEAbba();
        abba.contents = stream.readOctetString(length);
        return abba;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(contents);
    }
}
