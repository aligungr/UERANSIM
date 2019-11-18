package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.values.VPduSessionReactivationResultErrorCause;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEPduSessionReactivationResultErrorCause extends InformationElement6 {

    public VPduSessionReactivationResultErrorCause[] values;

    @Override
    protected IEPduSessionReactivationResultErrorCause decodeIE6(OctetInputStream stream, int length) {
        var res = new IEPduSessionReactivationResultErrorCause();
        if (length % 2 != 0)
            throw new DecodingException("length % 2 != 0");

        var values = new VPduSessionReactivationResultErrorCause[length / 2];
        for (int i = 0; i < values.length; i++) {
            values[i] = VPduSessionReactivationResultErrorCause.decode(stream);
        }

        res.values = values;
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        for (var item : values) {
            item.encode(stream);
        }
    }
}
