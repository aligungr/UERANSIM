package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EUnitForSessionAmbr;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet2;

public class IESessionAmbr extends InformationElement4 {
    public EUnitForSessionAmbr unitForSessionAmbrForDownlink;
    public Octet2 sessionAmbrForDownlink;
    public EUnitForSessionAmbr unitForSessionAmbrForUplink;
    public Octet2 sessionAmbrForUplink;


    @Override
    protected IESessionAmbr decodeIE4(OctetInputStream stream, int length) {
        var res = new IESessionAmbr();
        res.unitForSessionAmbrForDownlink = EUnitForSessionAmbr.fromValue(stream.readOctetI());
        res.sessionAmbrForDownlink = stream.readOctet2();
        res.unitForSessionAmbrForUplink = EUnitForSessionAmbr.fromValue(stream.readOctetI());
        res.sessionAmbrForUplink = stream.readOctet2();
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(unitForSessionAmbrForDownlink.intValue());
        stream.writeOctet2(sessionAmbrForDownlink);
        stream.writeOctet(unitForSessionAmbrForUplink.intValue());
        stream.writeOctet(sessionAmbrForUplink.intValue());
    }
}
