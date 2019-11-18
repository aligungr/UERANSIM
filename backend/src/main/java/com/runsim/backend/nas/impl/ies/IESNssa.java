package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
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
                res.sst = VSliceServiceType.decode(stream);
                break;
            case 0b00000010: // SST and mapped HPLMN SST
                res.sst = VSliceServiceType.decode(stream);
                throw new NotImplementedException("HPLMN SST not implemented yet");
            case 0b00000100: // SST and SD
                res.sst = VSliceServiceType.decode(stream);
                res.sd = VSliceDifferentiator.decode(stream);
                break;
            case 0b00000101: // SST, SD and mapped HPLMN SST
                res.sst = VSliceServiceType.decode(stream);
                res.sd = VSliceDifferentiator.decode(stream);
                throw new NotImplementedException("HPLMN SST not implemented yet");
            case 0b00001000: // SST, SD, mapped HPLMN SST and mapped HPLMN SD
                res.sst = VSliceServiceType.decode(stream);
                res.sd = VSliceDifferentiator.decode(stream);
                throw new NotImplementedException("HPLMN SST and HPLMN SD not implemented yet");
            default: // All other values are reserved
                throw new InvalidValueException("reserved value used");
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        // WARNING: Order is important.

        if (sst == null) {
            throw new EncodingException("sst cannot be null");
        }
        sst.encode(stream);

        if (sd != null) {
            sd.encode(stream);
        }
    }
}
