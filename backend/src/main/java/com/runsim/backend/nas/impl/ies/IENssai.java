package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;

public class IENssai extends InformationElement4 {
    public IESNssa[] sNssas;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var list = new ArrayList<IESNssa>();
        var res = new IENssai();
        int read = 0;

        while (read < length) {
            int subLength = stream.peekOctetI();
            list.add(NasDecoder.ie2346(stream, IESNssa.class));
            read += subLength + 1;
        }

        if (read > length)
            throw new DecodingException("read length exceeds the ie length");

        res.sNssas = list.toArray(new IESNssa[0]);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        if (sNssas == null)
            throw new EncodingException("S-NSSAs is null");
        for (var snssa : sNssas) {
            NasEncoder.ie2346(stream, snssa);
        }
    }
}
