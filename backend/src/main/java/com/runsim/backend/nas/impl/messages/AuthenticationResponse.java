package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IEAuthenticationResponseParameter;
import com.runsim.backend.nas.impl.ies.IEEapMessage;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class AuthenticationResponse extends PlainNasMessage {

    /* Optional fields */
    public IEAuthenticationResponseParameter authenticationResponseParameter;
    public IEEapMessage eapMessage;

    @Override
    public AuthenticationResponse decodeMessage(OctetInputStream stream) {
        var resp = new AuthenticationResponse();

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            switch (iei) {
                case 0x2D:
                    resp.authenticationResponseParameter = NasDecoder.ie2346(stream, IEAuthenticationResponseParameter.class);
                    break;
                case 0x78:
                    resp.eapMessage = NasDecoder.ie2346(stream, IEEapMessage.class);
                    break;
                default:
                    throw new InvalidValueException("Authentication Response Invalid Value: " + iei);
            }
        }

        return resp;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        if (authenticationResponseParameter != null)
            NasEncoder.ie2346(stream, 0x2D, authenticationResponseParameter);
        if (eapMessage != null) {
            NasEncoder.ie2346(stream, 0x78, eapMessage);
        }
    }
}
