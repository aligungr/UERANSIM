package com.runsim.backend.protocols.nas.impl.ie;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.exceptions.NotImplementedException;
import com.runsim.backend.protocols.nas.ielements.InformationElement4;

public class IEAuthenticationResponseParameter extends InformationElement4 {

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        throw new NotImplementedException("AuthenticationResponseParameter Not Implemented");
    }
}
