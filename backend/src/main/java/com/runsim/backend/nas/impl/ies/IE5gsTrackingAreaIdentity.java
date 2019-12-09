package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EMccValue;
import com.runsim.backend.nas.impl.enums.EMncValue;
import com.runsim.backend.nas.impl.values.VPlmn;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet3;

public class IE5gsTrackingAreaIdentity extends InformationElement3 {
    public EMccValue mcc;
    public EMncValue mnc;
    public Octet3 trackingAreaCode;

    public IE5gsTrackingAreaIdentity() {
    }

    public IE5gsTrackingAreaIdentity(EMccValue mcc, EMncValue mnc, Octet3 trackingAreaCode) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.trackingAreaCode = trackingAreaCode;
    }

    @Override
    protected IE5gsTrackingAreaIdentity decodeIE3(OctetInputStream stream) {
        var res = new IE5gsTrackingAreaIdentity();

        // WARNING: MCC and MNC are allowed to contain invalid characters for IE5gsTrackingAreaIdentity
        // but currently opposite of this is assumed.
        //
        // See 3GPP 24.501 f20, Table 9.11.3.8.1: 5GS tracking area identity information element
        VPlmn mccmnc = new VPlmn().decode(stream);

        res.mcc = mccmnc.mcc;
        res.mnc = mccmnc.mnc;
        res.trackingAreaCode = stream.readOctet3();
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        var mccmnc = new VPlmn();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);
        stream.writeOctet3(trackingAreaCode);
    }
}

