package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.*;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class IE5gsNetworkFeatureSupport extends InformationElement4 {
    public EImsVoPs3gpp imsVoPs3gpp;
    public EImsVoPsN3gpp imsVoPsN3gpp;
    public EEmergencyServiceSupport3gppIndicator emc;
    public EEmergencyServiceFallback3gppIndicator emf;
    public EInterworkingWithoutN26InterfaceIndicator iwkN26;
    public EMpsIndicator mpsi;
    public EEmergencyServiceSupportNon3gppIndicator emcn3;
    public EMcsIndicator mcsi;

    @Override
    protected IE5gsNetworkFeatureSupport decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsNetworkFeatureSupport();

        var octet = stream.readOctet();
        res.imsVoPs3gpp = EImsVoPs3gpp.fromValue(octet.getBitI(0));
        res.imsVoPsN3gpp = EImsVoPsN3gpp.fromValue(octet.getBitI(1));
        res.emc = EEmergencyServiceSupport3gppIndicator.fromValue(octet.getBitRangeI(2, 3));
        res.emf = EEmergencyServiceFallback3gppIndicator.fromValue(octet.getBitRangeI(4, 5));
        res.iwkN26 = EInterworkingWithoutN26InterfaceIndicator.fromValue(octet.getBitI(6));
        res.mpsi = EMpsIndicator.fromValue(octet.getBitI(7));
        octet = stream.readOctet();
        res.emcn3 = EEmergencyServiceSupportNon3gppIndicator.fromValue(octet.getBitI(0));
        res.mcsi = EMcsIndicator.fromValue(octet.getBitI(1));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBit(0, imsVoPs3gpp.intValue());
        octet = octet.setBit(1, imsVoPsN3gpp.intValue());
        octet = octet.setBitRange(2, 3, emc.intValue());
        octet = octet.setBitRange(4, 5, emf.intValue());
        octet = octet.setBit(1, iwkN26.intValue());
        octet = octet.setBit(1, mpsi.intValue());
        stream.writeOctet(octet);

        octet = new Octet();
        octet = octet.setBit(0, emcn3.intValue());
        octet = octet.setBit(1, mcsi.intValue());
        stream.writeOctet(octet);
    }
}
