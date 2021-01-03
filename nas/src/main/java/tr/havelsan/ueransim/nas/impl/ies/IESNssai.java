/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.values.VSliceDifferentiator;
import tr.havelsan.ueransim.nas.impl.values.VSliceServiceType;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.EncodingException;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;

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
