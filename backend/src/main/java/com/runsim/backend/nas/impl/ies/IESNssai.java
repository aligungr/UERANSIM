package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.exceptions.ReservedOrInvalidValueException;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VSliceDifferentiator;
import com.runsim.backend.nas.impl.values.VSliceServiceType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IESNssai extends InformationElement4 {
    public VSliceServiceType sst;
    public VSliceDifferentiator sd;
    public VSliceServiceType mappedHplmnSst;
    public VSliceDifferentiator mappedHplmnSd;

    public IESNssai() {
    }

    public IESNssai(VSliceServiceType sst, VSliceDifferentiator sd, VSliceServiceType mappedHplmnSst, VSliceDifferentiator mappedHplmnSd) {
        this.sst = sst;
        this.sd = sd;
        this.mappedHplmnSst = mappedHplmnSst;
        this.mappedHplmnSd = mappedHplmnSd;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IESNssai();

        switch (length) {
            case 0b00000001: // SST
                res.sst = new VSliceServiceType().decode(stream);
                break;
            case 0b00000010: // SST and mapped HPLMN SST
                res.sst = new VSliceServiceType().decode(stream);
                res.mappedHplmnSst = new VSliceServiceType().decode(stream);
                break;
            case 0b00000100: // SST and SD
                res.sst = new VSliceServiceType().decode(stream);
                res.sd = new VSliceDifferentiator().decode(stream);
                break;
            case 0b00000101: // SST, SD and mapped HPLMN SST
                res.sst = new VSliceServiceType().decode(stream);
                res.sd = new VSliceDifferentiator().decode(stream);
                res.mappedHplmnSst = new VSliceServiceType().decode(stream);
                break;
            case 0b00001000: // SST, SD, mapped HPLMN SST and mapped HPLMN SD
                res.sst = new VSliceServiceType().decode(stream);
                res.sd = new VSliceDifferentiator().decode(stream);
                res.mappedHplmnSst = new VSliceServiceType().decode(stream);
                res.mappedHplmnSd = new VSliceDifferentiator().decode(stream);
                break;
            default: // All other values are reserved
                throw new ReservedOrInvalidValueException("S-NSSAI Information Element Length Indicator");
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

        if (sd != null) sd.encode(stream);
        if (mappedHplmnSst != null) mappedHplmnSst.encode(stream);
        if (mappedHplmnSd != null) mappedHplmnSd.encode(stream);
    }
}
