package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.E5gMmRegistrationStatus;
import com.runsim.backend.nas.impl.enums.EEmmRegistrationStatus;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEUeStatus extends InformationElement4 {
    public EEmmRegistrationStatus s1ModeReg;
    public E5gMmRegistrationStatus n1ModeReg;

    @Override
    protected IEUeStatus decodeIE4(OctetInputStream stream, int length) {
        int octet = stream.readOctetI();

        var res = new IEUeStatus();
        res.s1ModeReg = EEmmRegistrationStatus.fromValue(octet);
        res.n1ModeReg = E5gMmRegistrationStatus.fromValue(octet >> 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = s1ModeReg.intValue() | (n1ModeReg.intValue() << 1);
        stream.writeOctet(octet);
    }
}
