package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode;
import com.runsim.backend.nas.impl.values.V5gTmsi;
import com.runsim.backend.nas.impl.values.VAmfSetId;
import com.runsim.backend.nas.impl.values.VPlmn;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit6;
import com.runsim.backend.utils.octets.Octet;

public class IE5gGutiMobileIdentity extends IE5gsMobileIdentity {
    public EMobileCountryCode mcc;
    public EMobileNetworkCode mnc;
    public Octet amfRegionId;
    public VAmfSetId amfSetId;
    public Bit6 amfPointer;
    public V5gTmsi tmsi;

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xf2); // Flags for 5G-GUTI

        /* Encode MCC and MNC*/
        var mccmnc = new VPlmn();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);

        /* Encode region id */
        stream.writeOctet(amfRegionId);

        /* Encode AMF set id and AMF pointer */
        var octets = amfSetId.toIntArray();
        octets[1] |= amfPointer.intValue();
        stream.writeOctets(octets);

        /* Encode TMSI */
        tmsi.encode(stream);
    }
}
