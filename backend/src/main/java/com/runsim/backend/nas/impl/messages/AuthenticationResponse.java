package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.nas.EapEncoder;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.EAP;
import com.runsim.backend.nas.impl.ies.IEAuthenticationResponseParameter;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class AuthenticationResponse extends PlainNasMessage {

    /* Optional fields */
    public IEAuthenticationResponseParameter authenticationResponseParameter;
    public EAP eap;

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

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        if (authenticationResponseParameter != null)
            NasEncoder.ie2346(stream, 0x2D, authenticationResponseParameter);
        if (eap != null) {
            stream.writeOctet(0x78);
            EapEncoder.eapPdu(stream, eap);
        }
    }
}
