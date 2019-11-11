package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode;
import com.runsim.backend.nas.impl.values.V5gTmsi;
import com.runsim.backend.nas.impl.values.VAmfSetId;
import com.runsim.backend.nas.impl.values.VMccMnc;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit6;
import com.runsim.backend.utils.octets.Octet;

public class IE5gGutiMobileIdentity extends IE5gsMobileIdentity {

    public EMobileCountryCode mobileCountryCode;
    public EMobileNetworkCode mobileNetworkCode;
    public Octet amfRegionId;
    public VAmfSetId amfSetId;
    public Bit6 amfPointer;
    public V5gTmsi tmsi;

    @Override
    public IE5gsMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        stream.readOctet();

        var result = new IE5gGutiMobileIdentity();

        /* Decode MCC and MNC */
        var mccmnc = NasDecoder.nasValue(stream, VMccMnc.class);
        result.mobileCountryCode = mccmnc.mobileCountryCode;
        result.mobileNetworkCode = mccmnc.mobileNetworkCode;

        /* Decode others */
        result.amfRegionId = stream.readOctet();
        result.amfSetId = NasDecoder.nasValue(stream, VAmfSetId.class);
        result.amfPointer = new Bit6(stream.readOctetI());
        result.tmsi = NasDecoder.nasValue(stream, V5gTmsi.class);

        return result;
    }


    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xf2); // Flags for 5G-GUTI

        /* Encode MCC and MNC*/
        var mccmnc = new VMccMnc();
        mccmnc.mobileCountryCode = mobileCountryCode;
        mccmnc.mobileNetworkCode = mobileNetworkCode;
        NasEncoder.nasValue(stream, mccmnc);

        /* Encode region id */
        stream.writeOctet(amfRegionId);

        /* Encode AMF set id and AMF pointer */
        var str = new OctetOutputStream();
        NasEncoder.nasValue(str, amfSetId);
        var bytes = str.toOctetArray();
        bytes[1] = new Octet((bytes[1].intValue() << 6) | amfPointer.intValue());
        stream.writeOctets(bytes);

        /* Encode TMSI */
        NasEncoder.nasValue(stream, tmsi);
    }

}
