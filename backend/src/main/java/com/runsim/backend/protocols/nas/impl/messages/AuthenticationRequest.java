package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.exceptions.NotImplementedException;
import com.runsim.backend.protocols.nas.DecodeUtils;
import com.runsim.backend.protocols.nas.impl.ie.IEAbba;
import com.runsim.backend.protocols.nas.impl.ie.IENasKeySetIdentifier;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class AuthenticationRequest extends PlainNasMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEAbba abba;
    public ExtensibleAuthenticationProtocol eap;

    @Override
    public AuthenticationRequest decodeMessage(OctetInputStream stream) {
        var req = new AuthenticationRequest();
        req.ngKSI = DecodeUtils.ie1(stream.readOctetI(), IENasKeySetIdentifier.class);
        req.abba = DecodeUtils.ie4(stream, false, IEAbba.class);

        while (stream.hasNext()) {
            int type = stream.readOctetI();
            switch (type) {
                case 0x21:
                    throw new NotImplementedException("RAND not implemented yet.");
                case 0x20:
                    throw new NotImplementedException("AUTN not implemented yet.");
                case 0x78:
                    req.eap = DecodeUtils.decodeExtensibleAuthenticationProtocol(stream);
                    break;
                default:
                    throw new InvalidValueException("Authentication request IEI is invalid: " + type);
            }
        }
        return req;
    }
}
