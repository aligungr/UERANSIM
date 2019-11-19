package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EEpsTypeOfCipheringAlgorithm;
import com.runsim.backend.nas.impl.enums.EEpsTypeOfIntegrityProtectionAlgorithm;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEEpsNasSecurityAlgorithms extends InformationElement3 {
    public EEpsTypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm;
    public EEpsTypeOfCipheringAlgorithm typeOfCipheringAlgorithm;

    @Override
    protected IEEpsNasSecurityAlgorithms decodeIE3(OctetInputStream stream) {
        var res = new IEEpsNasSecurityAlgorithms();
        res.typeOfIntegrityProtectionAlgorithm = EEpsTypeOfIntegrityProtectionAlgorithm.fromValue(stream.peekOctetI());
        res.typeOfCipheringAlgorithm = EEpsTypeOfCipheringAlgorithm.fromValue(stream.readOctetI() >> 4);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(typeOfCipheringAlgorithm.intValue(), typeOfIntegrityProtectionAlgorithm.intValue());
    }
}
