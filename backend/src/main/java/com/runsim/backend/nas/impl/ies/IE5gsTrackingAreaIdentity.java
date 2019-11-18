package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode;
import com.runsim.backend.nas.impl.values.VMccMnc;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet3;

public class IE5gsTrackingAreaIdentity extends InformationElement3 {

    public EMobileCountryCode mcc;
    public EMobileNetworkCode mnc;
    public Octet3 trackingAreaCode;

    @Override
    protected IE5gsTrackingAreaIdentity decodeIE3(OctetInputStream stream) {
        var res = new IE5gsTrackingAreaIdentity();
        VMccMnc mccmnc;

        // TODO: MCC and MNC are allowed to contain invalid characters for IE5gsTrackingAreaIdentity
        // but currently an exception is thrown if some invalid character found.
        //
        // See 3GPP 24.501 f20, Table 9.11.3.8.1: 5GS tracking area identity information element
        mccmnc = VMccMnc.decode(stream);

        res.mcc = mccmnc.mcc;
        res.mnc = mccmnc.mnc;
        res.trackingAreaCode = stream.readOctet3();
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        var mccmnc = new VMccMnc();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);
        stream.writeOctet3(trackingAreaCode);
    }
}

