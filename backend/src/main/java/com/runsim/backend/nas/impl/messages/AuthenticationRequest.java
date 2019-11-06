package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.Decoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.nas.impl.ies.IEAbba;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;
import com.runsim.backend.utils.OctetInputStream;

public class AuthenticationRequest extends PlainNasMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEAbba abba;
    public ExtensibleAuthenticationProtocol eap;

    @Override
    public AuthenticationRequest decodeMessage(OctetInputStream stream) {
        var req = new AuthenticationRequest();
        req.ngKSI = Decoder.ie1(stream.readOctetI(), IENasKeySetIdentifier.class);
        req.abba = Decoder.ie4(stream, false, IEAbba.class);

        while (stream.hasNext()) {
            int type = stream.readOctetI();
            switch (type) {
                case 0x21:
                    throw new NotImplementedException("RAND not implemented yet.");
                case 0x20:
                    throw new NotImplementedException("AUTN not implemented yet.");
                case 0x78:
                    req.eap = Decoder.eap(stream);
                    break;
                default:
                    throw new InvalidValueException("Authentication request IEI is invalid: " + type);
            }
        }
        return req;
    }
}
