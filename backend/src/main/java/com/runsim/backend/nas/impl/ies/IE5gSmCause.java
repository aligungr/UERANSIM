package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.ESmCause;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gSmCause extends InformationElement3 {
    public ESmCause value;

    public IE5gSmCause() {
    }

    public IE5gSmCause(ESmCause value) {
        this.value = value;
    }

    @Override
    protected IE5gSmCause decodeIE3(OctetInputStream stream) {
        var res = new IE5gSmCause();
        res.value = ESmCause.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(value.intValue());
    }
}
