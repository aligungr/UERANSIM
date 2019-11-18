package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VMccMnc;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEPlmnList extends InformationElement4 {

    // Minimum 1, maximum 15
    public VMccMnc[] plmns;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        if (length % 3 != 0)
            throw new DecodingException("cannot decode PLMN List, length mod 3 != 0");

        int arrLength = length > (15 * 3) ? 15 : length / 3;

        var res = new IEPlmnList();
        res.plmns = new VMccMnc[arrLength];

        for (int i = 0; i < arrLength; i++) {
            res.plmns[i] = VMccMnc.decode(stream);
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        if (plmns == null)
            return;
        for (var mccmnc : plmns) {
            mccmnc.encode(stream);
        }
    }
}
