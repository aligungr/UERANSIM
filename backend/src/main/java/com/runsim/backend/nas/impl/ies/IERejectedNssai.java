package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VRejectedSNssa;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;


public class IERejectedNssai extends InformationElement4 {

    public VRejectedSNssa[] rejectedSNSSAs;

    @Override
    protected IERejectedNssai decodeIE4(OctetInputStream stream, int length) {
        if (length % 5 != 0)
            throw new DecodingException("cannot decode Rejected NSSAI, length mod 5 != 0");

        // The number of rejected S-NSSAI(s) cannot exceed eight. (24.501 f20, 9.11.3.46)
        if (length > 8 * 5)
            length = 8 * 5;

        int arrLength = length / 5;

        var res = new IERejectedNssai();
        res.rejectedSNSSAs = new VRejectedSNssa[arrLength];

        for (int i = 0; i < arrLength; i++) {
            res.rejectedSNSSAs[i] = NasDecoder.nasValue(stream, VRejectedSNssa.class);
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        // The number of rejected S-NSSAI(s) cannot exceed eight. (24.501 f20, 9.11.3.46)
        int arrLength = Math.min(rejectedSNSSAs.length, 8);

        for (int i = 0; i < arrLength; i++) {
            NasEncoder.nasValue(stream, rejectedSNSSAs[i]);
        }
    }
}
