package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;

public class IELadnIndication extends InformationElement6 {
    public List<IEDnn> dnns;

    @Override
    protected IELadnIndication decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnIndication();
        res.dnns = new ArrayList<>();

        int startIndex = stream.currentIndex();
        while (stream.currentIndex() - startIndex < length) {
            res.dnns.add(NasDecoder.ie2346(stream, IEDnn.class));
        }

        if (stream.currentIndex() - startIndex > length) {
            throw new DecodingException("ie length exceeds the given length");
        }

        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        for (var dnn : dnns) {
            NasEncoder.ie2346(stream, dnn);
        }
    }
}
