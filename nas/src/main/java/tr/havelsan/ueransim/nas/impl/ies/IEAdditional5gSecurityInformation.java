/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEAdditional5gSecurityInformation extends InformationElement4 {
    public EHorizontalDerivationParameter hdp;
    public ERetransmissionOfInitialNasMessageRequest rinmr;

    public IEAdditional5gSecurityInformation() {
    }

    public IEAdditional5gSecurityInformation(EHorizontalDerivationParameter hdp, ERetransmissionOfInitialNasMessageRequest rinmr) {
        this.hdp = hdp;
        this.rinmr = rinmr;
    }

    @Override
    protected IEAdditional5gSecurityInformation decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAdditional5gSecurityInformation();
        res.hdp = EHorizontalDerivationParameter.fromValue(stream.peekOctetI() & 0b1);
        res.rinmr = ERetransmissionOfInitialNasMessageRequest.fromValue(stream.readOctetI() >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(rinmr.intValue() << 1 | hdp.intValue());
    }

    public static class ERetransmissionOfInitialNasMessageRequest extends ProtocolEnum {
        public static final ERetransmissionOfInitialNasMessageRequest NOT_REQUESTED
                = new ERetransmissionOfInitialNasMessageRequest(0b0, "Retransmission of the initial NAS message not requested");
        public static final ERetransmissionOfInitialNasMessageRequest REQUESTED
                = new ERetransmissionOfInitialNasMessageRequest(0b1, "Retransmission of the initial NAS message requested");

        private ERetransmissionOfInitialNasMessageRequest(int value, String name) {
            super(value, name);
        }

        public static ERetransmissionOfInitialNasMessageRequest fromValue(int value) {
            return fromValueGeneric(ERetransmissionOfInitialNasMessageRequest.class, value, null);
        }
    }

    public static class EHorizontalDerivationParameter extends ProtocolEnum {
        public static final EHorizontalDerivationParameter NOT_REQUIRED
                = new EHorizontalDerivationParameter(0b0, "K_AMF derivation is not required");
        public static final EHorizontalDerivationParameter REQUIRED
                = new EHorizontalDerivationParameter(0b1, "K_AMF derivation is required");

        private EHorizontalDerivationParameter(int value, String name) {
            super(value, name);
        }

        public static EHorizontalDerivationParameter fromValue(int value) {
            return fromValueGeneric(EHorizontalDerivationParameter.class, value, null);
        }
    }
}
