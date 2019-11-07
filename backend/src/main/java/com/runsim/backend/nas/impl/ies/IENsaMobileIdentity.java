package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IENsaMobileIdentity extends IESuciMobileIdentity {
    @Override
    public IENsaMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        throw new NotImplementedException("NetworkSpecificIdentifier not implemented yet");
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("NetworkSpecificIdentifier not implemented yet");
    }
}
