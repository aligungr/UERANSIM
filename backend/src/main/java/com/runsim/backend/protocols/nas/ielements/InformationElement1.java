package com.runsim.backend.protocols.nas.ielements;

import com.runsim.backend.protocols.bits.Bit4;
import com.runsim.backend.protocols.core.OctetInputStream;

public abstract class InformationElement1 extends InformationElement {
    public abstract InformationElement1 decodeIE1(Bit4 value);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent) {
        int octet = stream.readOctetI();
        int iei = octet >> 4 & 0xF;
        int value = octet & 0xF;
        return decodeIE1(new Bit4(value));
    }
}
