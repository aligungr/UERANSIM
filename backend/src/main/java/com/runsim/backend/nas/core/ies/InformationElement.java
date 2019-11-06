package com.runsim.backend.nas.core.ies;

import com.runsim.backend.utils.OctetInputStream;

public abstract class InformationElement {
    public abstract InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent);
}
