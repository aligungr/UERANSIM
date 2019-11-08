package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.nas.impl.ies.IEAuthenticationResponseParameter;
import com.runsim.backend.utils.OctetInputStream;

public class AuthenticationResponse extends PlainNasMessage {

    /* Optional fields */
    public IEAuthenticationResponseParameter authenticationResponseParameter;
    public ExtensibleAuthenticationProtocol eap;

    @Override
    public AuthenticationResponse decodeMessage(OctetInputStream stream) {
        var resp = new AuthenticationResponse();

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            switch (iei) {
                case 0x2D:
                    resp.authenticationResponseParameter = NasDecoder.ie2346(stream, false, IEAuthenticationResponseParameter.class);
                    break;
                case 0x78:
                    resp.eap = NasDecoder.eap(stream);
                    break;
                default:
                    throw new InvalidValueException("Authentication Response Invalid Value: " + iei);
            }
        }

        return resp;
    }
}
