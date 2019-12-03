package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.Arrays;

public class IENssai extends InformationElement4 {
    public IESNssai[] sNssas;

    public IENssai() {
    }

    public IENssai(IESNssai[] sNssas) {
        this.sNssas = sNssas;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IENssai();
        res.sNssas = Utils.decodeList(stream, stream1 -> NasDecoder.ie2346(stream1, IESNssai.class), length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(sNssas).forEach(snssa -> NasEncoder.ie2346(stream, snssa));
    }
}
