package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.nas.EapEncoder;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.nas.impl.ies.IEAbba;
import com.runsim.backend.nas.impl.ies.IEAutn;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;
import com.runsim.backend.nas.impl.ies.IERand;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit4;

public class AuthenticationRequest extends PlainNasMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEAbba abba;

    /* Optional fields */
    public ExtensibleAuthenticationProtocol eap;
    public IERand authParamRAND;
    public IEAutn authParamAUTN;

    @Override
    public AuthenticationRequest decodeMessage(OctetInputStream stream) {
        var req = new AuthenticationRequest();
        req.ngKSI = NasDecoder.ie1(stream.readOctetI(), IENasKeySetIdentifier.class);
        req.abba = NasDecoder.ie2346(stream, false, IEAbba.class);

        while (stream.hasNext()) {
            int type = stream.readOctetI();
            switch (type) {
                case 0x21:
                    authParamRAND = NasDecoder.ie2346(stream, false, IERand.class);
                    break;
                case 0x20:
                    authParamAUTN = NasDecoder.ie2346(stream, false, IEAutn.class);
                    break;
                case 0x78:
                    req.eap = NasDecoder.eap(stream);
                    break;
                default:
                    throw new InvalidValueException("Authentication request IEI is invalid: " + type);
            }
        }
        return req;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        NasEncoder.ie1(stream, new Bit4(0), ngKSI);
        NasEncoder.ie2346(stream, abba);

        if (eap != null) {
            stream.writeOctet(0x78);
            EapEncoder.eapPdu(stream, eap);
        }
    }
}
