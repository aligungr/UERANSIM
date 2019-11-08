package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;
import com.runsim.backend.nas.impl.ies.IE5gsRegistrationResult;
import com.runsim.backend.nas.impl.ies.IENssa;
import com.runsim.backend.nas.impl.ies.IEPduSessionStatus;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class RegistrationAccept extends PlainNasMessage {

    public IE5gsRegistrationResult registrationResult;

    /* Optional fields */
    public IE5gsMobileIdentity mobileIdentity;
    public IENssa allowedNSSA;
    public IEPduSessionStatus pduSessionStatus;

    @Override
    public RegistrationAccept decodeMessage(OctetInputStream stream) {
        var resp = new RegistrationAccept();
        resp.registrationResult = NasDecoder.ie2346(stream, false, IE5gsRegistrationResult.class);

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            int msb = iei >> 4 & 0xF;
            if (msb == 0xA || msb == 0xB || msb == 0x9) {
                int lsb = iei & 0xF;
                switch (msb) {
                    case 0xA:
                        throw new NotImplementedException("NSSAI inclusion mode not implemented yet");
                    case 0xB:
                        throw new NotImplementedException("MICO indication not implemented yet");
                    case 0x9:
                        throw new NotImplementedException("Network slicing indication not implemented yet");
                }
            } else {
                switch (iei) {
                    case 0x77:
                        resp.mobileIdentity = NasDecoder.mobileIdentity(stream, false);
                        break;
                    case 0x4A:
                        throw new NotImplementedException("Equivalent PLMNs not implemented yet");
                    case 0x54:
                        throw new NotImplementedException("TAI list not implemented yet");
                    case 0x15:
                        resp.allowedNSSA = NasDecoder.ie2346(stream, false, IENssa.class);
                        break;
                    case 0x11:
                        throw new NotImplementedException("Rejected NSSAI not implemented yet");
                    case 0x31:
                        throw new NotImplementedException("Configured NSSAI not implemented yet");
                    case 0x21:
                        throw new NotImplementedException("5GS network feature support not implemented yet");
                    case 0x50:
                        resp.pduSessionStatus = NasDecoder.ie2346(stream, false, IEPduSessionStatus.class);
                        break;
                    case 0x26:
                        throw new NotImplementedException("PDU session reactivation result not implemented yet");
                    case 0x72:
                        throw new NotImplementedException("PDU session reactivation result error cause not implemented yet");
                    case 0x79:
                        throw new NotImplementedException("LADN information not implemented yet");
                    case 0x27:
                        throw new NotImplementedException("Service area list not implemented yet");
                    case 0x5E:
                        throw new NotImplementedException("T3512 value not implemented yet");
                    case 0x5D:
                        throw new NotImplementedException("Non-3GPP de-registration timer value not implemented yet");
                    case 0x16:
                        throw new NotImplementedException("T3502 value not implemented yet");
                    case 0x34:
                        throw new NotImplementedException("Emergency number list not implemented yet");
                    case 0x7A:
                        throw new NotImplementedException("Extended emergency number list not implemented yet");
                    case 0x73:
                        throw new NotImplementedException("SOR transparent container not implemented yet");
                    case 0x78:
                        throw new NotImplementedException("EAP message not implemented yet");
                    case 0x76:
                        throw new NotImplementedException("Operator-defined access category definitions not implemented yet");
                    case 0x51:
                        throw new NotImplementedException("Negotiated DRX parameters not implemented yet");
                    default:
                        throw new InvalidValueException("iei is invalid: " + iei);
                }
            }
        }

        return resp;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        NasEncoder.ie2346(stream, registrationResult);
        if (mobileIdentity != null)
            NasEncoder.ie2346(stream, 0x77, mobileIdentity);
        if (allowedNSSA != null)
            NasEncoder.ie2346(stream, 0x15, allowedNSSA);
        if (pduSessionStatus != null)
            NasEncoder.ie2346(stream, 0x50, pduSessionStatus);
    }
}
