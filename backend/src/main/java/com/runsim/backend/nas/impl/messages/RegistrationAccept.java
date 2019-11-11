package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.EapDecoder;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.EAP;
import com.runsim.backend.nas.impl.ies.*;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class RegistrationAccept extends PlainNasMessage {

    public IE5gsRegistrationResult registrationResult;

    /* Optional fields */
    public IE5gsMobileIdentity mobileIdentity;
    public IENssai allowedNSSAI;
    public IEPduSessionStatus pduSessionStatus;
    public EAP eap;
    public IENssaiInclusionMode nssaiInclusionMode;
    public IENetworkSlicingIndication networkSlicingIndication;
    public IEMicoIndication micoIndication;
    public IEPlmnList equivalentPLMNs;
    public IERejectedNssai rejectedNSSAI;
    public IENssai configuredNSSAI;
    public IE5gsNetworkFeatureSupport networkFeatureSupport;
    public IEPduSessionReactivationResult pduSessionReactivationResult;
    public IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause;
    public IEGprsTimer3 t3512Value;
    public IEGprsTimer2 t3502Value;
    public IEGprsTimer2 non3gppDeRegistrationTimerValue;
    public IE5gsDrxParameters negotiatedDrxParameters;

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
                    case 0x9:
                        this.networkSlicingIndication = NasDecoder.ie1(lsb, IENetworkSlicingIndication.class);
                        break;
                    case 0xA:
                        this.nssaiInclusionMode = NasDecoder.ie1(lsb, IENssaiInclusionMode.class);
                        break;
                    case 0xB:
                        this.micoIndication = NasDecoder.ie1(lsb, IEMicoIndication.class);
                        break;
                }
            } else {
                switch (iei) {
                    case 0x77:
                        resp.mobileIdentity = NasDecoder.mobileIdentity(stream, false);
                        break;
                    case 0x4A:
                        resp.equivalentPLMNs = NasDecoder.ie2346(stream, false, IEPlmnList.class);
                        break;
                    case 0x54:
                        throw new NotImplementedException("TAI list not implemented yet");
                    case 0x15:
                        resp.allowedNSSAI = NasDecoder.ie2346(stream, false, IENssai.class);
                        break;
                    case 0x11:
                        resp.rejectedNSSAI = NasDecoder.ie2346(stream, false, IERejectedNssai.class);
                        break;
                    case 0x31:
                        resp.configuredNSSAI = NasDecoder.ie2346(stream, false, IENssai.class);
                        break;
                    case 0x21:
                        resp.networkFeatureSupport = NasDecoder.ie2346(stream, false, IE5gsNetworkFeatureSupport.class);
                        break;
                    case 0x50:
                        resp.pduSessionStatus = NasDecoder.ie2346(stream, false, IEPduSessionStatus.class);
                        break;
                    case 0x26:
                        resp.pduSessionReactivationResult = NasDecoder.ie2346(stream, false, IEPduSessionReactivationResult.class);
                        break;
                    case 0x72:
                        resp.pduSessionReactivationResultErrorCause = NasDecoder.ie2346(stream, false, IEPduSessionReactivationResultErrorCause.class);
                        break;
                    case 0x79:
                        throw new NotImplementedException("LADN information not implemented yet");
                    case 0x27:
                        throw new NotImplementedException("Service area list not implemented yet");
                    case 0x5E:
                        resp.t3512Value = NasDecoder.ie2346(stream, false, IEGprsTimer3.class);
                        break;
                    case 0x5D:
                        resp.non3gppDeRegistrationTimerValue = NasDecoder.ie2346(stream, false, IEGprsTimer2.class);
                        break;
                    case 0x16:
                        resp.t3502Value = NasDecoder.ie2346(stream, false, IEGprsTimer2.class);
                        break;
                    case 0x34:
                        throw new NotImplementedException("Emergency number list not implemented yet");
                    case 0x7A:
                        throw new NotImplementedException("Extended emergency number list not implemented yet");
                    case 0x73:
                        throw new NotImplementedException("SOR transparent container not implemented yet");
                    case 0x78:
                        this.eap = EapDecoder.eapPdu(stream);
                        break;
                    case 0x76:
                        throw new NotImplementedException("Operator-defined access category definitions not implemented yet");
                    case 0x51:
                        this.negotiatedDrxParameters = NasDecoder.ie2346(stream, false, IE5gsDrxParameters.class);
                        break;
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
        if (allowedNSSAI != null)
            NasEncoder.ie2346(stream, 0x15, allowedNSSAI);
        if (pduSessionStatus != null)
            NasEncoder.ie2346(stream, 0x50, pduSessionStatus);
    }
}
