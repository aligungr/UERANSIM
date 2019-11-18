package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.impl.values.V5gTmsi;
import com.runsim.backend.nas.impl.values.VAmfSetId;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit6;
import com.runsim.backend.utils.octets.Octet;

public class IE5gTmsiMobileIdentity extends IE5gsMobileIdentity {
    public VAmfSetId amfSetId;
    public Bit6 amfPointer;
    public V5gTmsi tmsi;

    @Override
    public IE5gTmsiMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        var res = new IE5gTmsiMobileIdentity();
        res.amfSetId = VAmfSetId.decode(stream);
        res.amfPointer = new Bit6(stream.readOctetI());
        res.tmsi = V5gTmsi.decode(stream);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0b11110100); // Flags for 5G-TMSI

        /* Encode AMF set id and AMF pointer */
        var str = new OctetOutputStream();
        amfSetId.encode(str);
        var bytes = str.toOctetArray();
        bytes[1] = new Octet((bytes[1].intValue() << 6) | amfPointer.intValue());
        stream.writeOctets(bytes);

        /* Encode TMSI */
        tmsi.encode(stream);
    }
}
