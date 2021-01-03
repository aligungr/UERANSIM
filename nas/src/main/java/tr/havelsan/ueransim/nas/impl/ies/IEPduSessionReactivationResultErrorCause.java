/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.DecodingException;

public class IEPduSessionReactivationResultErrorCause extends InformationElement6 {
    public VPduSessionReactivationResultErrorCause[] values;

    public IEPduSessionReactivationResultErrorCause() {
    }

    public IEPduSessionReactivationResultErrorCause(VPduSessionReactivationResultErrorCause[] values) {
        this.values = values;
    }

    @Override
    protected IEPduSessionReactivationResultErrorCause decodeIE6(OctetInputStream stream, int length) {
        var res = new IEPduSessionReactivationResultErrorCause();
        if (length % 2 != 0)
            throw new DecodingException("length % 2 != 0");

        var values = new VPduSessionReactivationResultErrorCause[length / 2];
        for (int i = 0; i < values.length; i++) {
            values[i] = new VPduSessionReactivationResultErrorCause().decode(stream);
        }

        res.values = values;
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        for (var item : values) {
            item.encode(stream);
        }
    }

    public static class VPduSessionReactivationResultErrorCause extends NasValue {
        public EPduSessionIdentity pduSessionId;
        public EMmCause causeValue;

        public VPduSessionReactivationResultErrorCause() {
        }

        public VPduSessionReactivationResultErrorCause(EPduSessionIdentity pduSessionId, EMmCause causeValue) {
            this.pduSessionId = pduSessionId;
            this.causeValue = causeValue;
        }

        @Override
        public VPduSessionReactivationResultErrorCause decode(OctetInputStream stream) {
            var res = new VPduSessionReactivationResultErrorCause();
            res.pduSessionId = EPduSessionIdentity.fromValue(stream.readOctetI());
            res.causeValue = EMmCause.fromValue(stream.readOctetI());
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            stream.writeOctet(pduSessionId.intValue());
            stream.writeOctet(causeValue.intValue());
        }
    }
}
