package com.runsim.backend.protocols.nas.impl.ie;

import com.runsim.backend.protocols.bits.Bit3;
import com.runsim.backend.protocols.bits.Bit4;
import com.runsim.backend.protocols.nas.ielements.InformationElement1;
import com.runsim.backend.protocols.nas.impl.enums.ETypeOfSecurityContext;

public class IENasKeySetIdentifier extends InformationElement1 {
    /*
     * 'no key is available' for UE to network
     * 'reserved' for network to UE
     */
    public static final Bit3 NOT_AVAILABLE_OR_RESERVED = new Bit3(0b111);

    public ETypeOfSecurityContext tsc;
    public Bit3 nasKeySetIdentifier;

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        int val = value.intValue;

        var res = new IENasKeySetIdentifier();
        res.tsc = ETypeOfSecurityContext.fromValue(val >> 3 & 0b1);
        res.nasKeySetIdentifier = new Bit3(val & 0b111);
        return res;
    }
}
