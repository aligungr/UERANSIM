package com.runsim.backend.protocols.nas.ielements;

import com.runsim.backend.protocols.core.OctetInputStream;

public abstract class InformationElement {
    public abstract InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent);
}
