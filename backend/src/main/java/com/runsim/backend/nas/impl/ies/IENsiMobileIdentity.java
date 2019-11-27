package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.utils.OctetOutputStream;

public class IENsiMobileIdentity extends IESuciMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("NetworkSpecificIdentifier not implemented yet");
    }
}
