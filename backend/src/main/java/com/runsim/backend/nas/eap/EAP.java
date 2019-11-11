package com.runsim.backend.nas.eap;

import com.runsim.backend.nas.core.ProtocolValue;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet2;

public abstract class EAP extends ProtocolValue {
    public ECode code;
    public Octet id;
    public Octet2 length;
    public EEapType EAPType;
}
