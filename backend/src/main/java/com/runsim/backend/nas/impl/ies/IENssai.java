package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.Arrays;

public class IENssai extends InformationElement4 {
    public IESNssai[] sNssais;

    public IENssai() {
    }

    public IENssai(IESNssai[] sNssais) {
        this.sNssais = sNssais;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IENssai();
        res.sNssais = Utils.decodeList(stream, s -> NasDecoder.ie2346(s, IESNssai.class), length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(sNssais).forEach(snssa -> NasEncoder.ie2346(stream, snssa));
    }
}
