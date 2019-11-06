package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.nas.DecodeUtils;
import com.runsim.backend.protocols.nas.impl.ie.IEAuthenticationResponseParameter;
import com.runsim.backend.protocols.nas.messages.NasMessage;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class AuthenticationResponse extends PlainNasMessage {

    /* Optional fields */
    public IEAuthenticationResponseParameter authenticationResponseParameter;
    public ExtensibleAuthenticationProtocol eap;

    @Override
    public NasMessage decodeMessage(OctetInputStream stream) {
        var resp = new AuthenticationResponse();

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            switch (iei) {
                case 0x2D:
                    resp.authenticationResponseParameter = DecodeUtils.ie4(stream, false, IEAuthenticationResponseParameter.class);
                    break;
                case 0x78:
                    resp.eap = DecodeUtils.decodeExtensibleAuthenticationProtocol(stream);
                    break;
                default:
                    throw new InvalidValueException("Authentication Response Invalid Value: " + iei);
            }
        }

        return resp;
    }
}
