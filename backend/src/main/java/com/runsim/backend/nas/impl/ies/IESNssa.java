package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VSliceDifferentiator;
import com.runsim.backend.nas.impl.values.VSliceServiceType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IESNssa extends InformationElement4 {

    public VSliceServiceType sst;
    public VSliceDifferentiator sd;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IESNssa();

        switch (length) {
            case 0b00000001: // SST
                res.sst = NasDecoder.nasValue(stream, VSliceServiceType.class);
                break;
            case 0b00000010: // SST and mapped HPLMN SST
                res.sst = NasDecoder.nasValue(stream, VSliceServiceType.class);
                throw new NotImplementedException("HPLMN SST not implemented yet");
            case 0b00000100: // SST and SD
                res.sst = NasDecoder.nasValue(stream, VSliceServiceType.class);
                res.sd = NasDecoder.nasValue(stream, VSliceDifferentiator.class);
                break;
            case 0b00000101: // SST, SD and mapped HPLMN SST
                res.sst = NasDecoder.nasValue(stream, VSliceServiceType.class);
                res.sd = NasDecoder.nasValue(stream, VSliceDifferentiator.class);
                throw new NotImplementedException("HPLMN SST not implemented yet");
            case 0b00001000: // SST, SD, mapped HPLMN SST and mapped HPLMN SD
                res.sst = NasDecoder.nasValue(stream, VSliceServiceType.class);
                res.sd = NasDecoder.nasValue(stream, VSliceDifferentiator.class);
                throw new NotImplementedException("HPLMN SST and HPLMN SD not implemented yet");
            default: // All other values are reserved
                throw new InvalidValueException("reserved value used");
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
