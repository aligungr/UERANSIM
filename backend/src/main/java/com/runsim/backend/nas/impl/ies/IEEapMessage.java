package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.EapEncoder;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.eap.EAP;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEEapMessage extends InformationElement6 {
    public EAP eap;

    @Override
    protected IEEapMessage decodeIE6(OctetInputStream stream, int length) {
        var res = new IEEapMessage();
        res.eap = NasDecoder.eap(stream);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        EapEncoder.eapPdu(stream, eap);
    }
}
