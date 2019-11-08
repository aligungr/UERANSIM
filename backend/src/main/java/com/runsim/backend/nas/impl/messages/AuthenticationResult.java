package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.nas.Decoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.nas.impl.ies.IEAbba;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;
import com.runsim.backend.utils.OctetInputStream;

public class AuthenticationResult extends PlainNasMessage {

    public IENasKeySetIdentifier ngKSI;
    public ExtensibleAuthenticationProtocol eap;

    /* Optional fields */
    public IEAbba abba;

    @Override
    public AuthenticationResult decodeMessage(OctetInputStream stream) {
        var res = new AuthenticationResult();
        res.ngKSI = Decoder.ie1(stream.readOctetI(), IENasKeySetIdentifier.class);
        res.eap = Decoder.eap(stream);

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            switch (iei) {
                case 0x38:
                    res.abba = Decoder.ie2346(stream, false, IEAbba.class);
                    break;
                default:
                    throw new InvalidValueException("invalid iei for Authentication Result: " + iei);
            }
        }

        return res;
    }
}
