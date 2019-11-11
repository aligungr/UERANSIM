package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EEpcNasSupported;
import com.runsim.backend.nas.impl.enums.EHandoverAttachSupported;
import com.runsim.backend.nas.impl.enums.ELtePositioningProtocolCapability;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gMmCapability extends InformationElement4 {

    public EEpcNasSupported s1Mode;
    public EHandoverAttachSupported hoAttach;
    public ELtePositioningProtocolCapability lpp;

    @Override
    protected IE5gMmCapability decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gMmCapability();
        var octet = stream.readOctet();
        s1Mode = EEpcNasSupported.fromValue(octet.getBitI(0));
        hoAttach = EHandoverAttachSupported.fromValue(octet.getBitI(1));
        lpp = ELtePositioningProtocolCapability.fromValue(octet.getBitI(2));
        // other octets are spare (if any)
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = 0;
        octet |= lpp.value;
        octet <<= 1;
        octet |= hoAttach.value;
        octet <<= 1;
        octet |= s1Mode.value;
        stream.writeOctet(octet);
    }
}
