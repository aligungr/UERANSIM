/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit11;

public class IEMaximumNumberOfSupportedPacketFilters extends InformationElement3 {
    public Bit11 value;

    public IEMaximumNumberOfSupportedPacketFilters() {
    }

    public IEMaximumNumberOfSupportedPacketFilters(Bit11 value) {
        this.value = value;
    }

    @Override
    protected IEMaximumNumberOfSupportedPacketFilters decodeIE3(OctetInputStream stream) {
        int value = stream.readOctet2I();
        value >>= 5;

        var res = new IEMaximumNumberOfSupportedPacketFilters();
        res.value = new Bit11(value);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeBits(value);
    }
}
