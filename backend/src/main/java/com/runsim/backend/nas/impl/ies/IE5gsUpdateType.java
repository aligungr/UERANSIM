package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.ENgRanRadioCapabilityUpdate;
import com.runsim.backend.nas.impl.enums.ESmsRequested;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class IE5gsUpdateType extends InformationElement4 {
    public ESmsRequested smsRequested;
    public ENgRanRadioCapabilityUpdate ngRanRcu;

    @Override
    protected IE5gsUpdateType decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsUpdateType();
        res.smsRequested = ESmsRequested.fromValue(stream.peekOctetI());
        res.ngRanRcu = ENgRanRadioCapabilityUpdate.fromValue(stream.readOctetI() >> 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBit(0, smsRequested.intValue());
        octet = octet.setBit(1, ngRanRcu.intValue());
        stream.writeOctet(octet.intValue());
    }
}
