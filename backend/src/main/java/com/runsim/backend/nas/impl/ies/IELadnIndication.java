package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.Arrays;

public class IELadnIndication extends InformationElement6 {
    public IEDnn[] dnns;

    public IELadnIndication() {
    }

    public IELadnIndication(IEDnn[] dnns) {
        this.dnns = dnns;
    }

    @Override
    protected IELadnIndication decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnIndication();
        res.dnns = Utils.decodeList(stream, s -> NasDecoder.ie2346(s, IEDnn.class), length, IEDnn.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        Arrays.stream(dnns).forEach(dnn -> NasEncoder.ie2346(stream, dnn));
    }
}
