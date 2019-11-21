package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.values.VMappedEpsBearerContext;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;

public class IEMappedEpsBearerContexts extends InformationElement6 {
    public List<VMappedEpsBearerContext> mappedEpsBearerContexts;

    @Override
    protected IEMappedEpsBearerContexts decodeIE6(OctetInputStream stream, int length) {
        var res = new IEMappedEpsBearerContexts();
        res.mappedEpsBearerContexts = new ArrayList<>();

        int readLen = 0;

        while (readLen < length) {
            int streamIndex = stream.currentIndex();
            res.mappedEpsBearerContexts.add(VMappedEpsBearerContext.decode(stream));
            readLen += stream.currentIndex() - streamIndex;
        }
        if (readLen > length) throw new DecodingException("Value length exceeds total length!");

        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        mappedEpsBearerContexts.forEach(ctx -> ctx.encode(stream));
    }
}
