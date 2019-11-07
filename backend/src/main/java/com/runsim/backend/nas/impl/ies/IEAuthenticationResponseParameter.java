package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEAuthenticationResponseParameter extends InformationElement4 {

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        throw new NotImplementedException("AuthenticationResponseParameter Not Implemented");
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
