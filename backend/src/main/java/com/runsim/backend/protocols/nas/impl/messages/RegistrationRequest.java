package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.exceptions.NotImplementedException;
import com.runsim.backend.protocols.nas.DecodeUtils;
import com.runsim.backend.protocols.nas.impl.ie.IE5gsMobileIdentity;
import com.runsim.backend.protocols.nas.impl.ie.IE5gsRegistrationType;
import com.runsim.backend.protocols.nas.impl.ie.IENasKeySetIdentifier;
import com.runsim.backend.protocols.nas.impl.values.UeSecurityCapability;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class RegistrationRequest extends PlainNasMessage {
    public IE5gsRegistrationType registrationType;
    public IENasKeySetIdentifier nasKeySetIdentifier;
    public IE5gsMobileIdentity mobileIdentity;

    /* Optional fields */
    public UeSecurityCapability ueSecurityCapability;

    @Override
    public RegistrationRequest decodeMessage(OctetInputStream stream) {
        var req = new RegistrationRequest();

        int octet = stream.readOctetI();
        req.registrationType = DecodeUtils.ie1(octet & 0xF, IE5gsRegistrationType.class);
        req.nasKeySetIdentifier = DecodeUtils.ie1(octet >> 4, IENasKeySetIdentifier.class);
        req.mobileIdentity = DecodeUtils.ie6(stream, false, IE5gsMobileIdentity.class);

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            // TODO: Specte iei olarak "C-", "B-", "9-" yazanlar ihmal edildi.
            switch (iei) {
                case 0x10:
                    throw new NotImplementedException("5GMM capability not implemented yet");
                case 0x2E:
                    req.ueSecurityCapability = new UeSecurityCapability().decodeUESecurityCapability(stream);
                    break;
                case 0x2F:
                    throw new NotImplementedException("not implemented yet: Requested NSSAI");
                case 0x52:
                    throw new NotImplementedException("not implemented yet: Last visited registered TAI");
                case 0x17:
                    throw new NotImplementedException("not implemented yet: S1 UE network capability");
                case 0x40:
                    throw new NotImplementedException("not implemented yet: Uplink data status");
                case 0x50:
                    throw new NotImplementedException("not implemented yet: PDU session status");
                case 0x2B:
                    throw new NotImplementedException("not implemented yet: UE status");
                case 0x77:
                    throw new NotImplementedException("not implemented yet: Additional GUTI");
                case 0x25:
                    throw new NotImplementedException("not implemented yet: Allowed PDU session status");
                case 0x18:
                    throw new NotImplementedException("not implemented yet: UE's usage setting");
                case 0x51:
                    throw new NotImplementedException("not implemented yet: Requested DRX parameters");
                case 0x70:
                    throw new NotImplementedException("not implemented yet: EPS NAS message container");
                case 0x7E:
                    throw new NotImplementedException("not implemented yet: LADN indication");
                case 0x7B:
                    throw new NotImplementedException("not implemented yet: Payload container");
                case 0x53:
                    throw new NotImplementedException("not implemented yet: 5GS update type");
                case 0x71:
                    throw new NotImplementedException("not implemented yet: NAS message container");
                default:
                    throw new InvalidValueException("iei is invalid: " + iei);
            }
        }

        return req;
    }
}
