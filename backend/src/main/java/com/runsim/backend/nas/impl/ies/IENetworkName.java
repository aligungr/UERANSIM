package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EAddCountryInitials;
import com.runsim.backend.nas.impl.enums.ECodingScheme;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit3;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.OctetString;

public class IENetworkName extends InformationElement4 {
    public Bit3 numOfSpareBits;
    public EAddCountryInitials addCi;
    public ECodingScheme codingScheme;
    public OctetString textString;

    @Override
    protected IENetworkName decodeIE4(OctetInputStream stream, int length) {
        int flags = stream.readOctetI();

        var res = new IENetworkName();
        res.numOfSpareBits = new Bit3(flags);
        res.addCi = EAddCountryInitials.fromValue(flags >> 3);
        res.codingScheme = ECodingScheme.fromValue(flags >> 4);
        res.textString = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var flags = new Octet();
        flags = flags.setBitRange(0, 2, numOfSpareBits.intValue());
        flags = flags.setBit(3, addCi.intValue());
        flags = flags.setBitRange(4, 6, codingScheme.intValue());
        stream.writeOctet(flags);
        stream.writeOctetString(textString);
    }
}
