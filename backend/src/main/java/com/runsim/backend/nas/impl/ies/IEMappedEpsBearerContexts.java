package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.values.VMappedEpsBearerContext;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IEMappedEpsBearerContexts extends InformationElement6 {
    public List<VMappedEpsBearerContext> mappedEpsBearerContexts;

    @Override
    protected IEMappedEpsBearerContexts decodeIE6(OctetInputStream stream, int length) {
        var res = new IEMappedEpsBearerContexts();
        res.mappedEpsBearerContexts = Utils.decodeList(stream, VMappedEpsBearerContext::decode, 0, length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        mappedEpsBearerContexts.forEach(ctx -> ctx.encode(stream));
    }
}
