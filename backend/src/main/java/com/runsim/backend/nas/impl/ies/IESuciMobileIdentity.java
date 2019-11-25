package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class IESuciMobileIdentity extends IE5gsMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new IncorrectImplementationException("subtypes must override this method");
    }
}
