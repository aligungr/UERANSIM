package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EReflectiveQoS;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gSmCapability extends InformationElement4 {
    public EReflectiveQoS rqos;
    public EMultiHomedIPv6PduSession mh6pdu;

    @Override
    protected IE5gSmCapability decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gSmCapability();
        res.rqos = EReflectiveQoS.fromValue(stream.peekOctetI());
        res.mh6pdu = EMultiHomedIPv6PduSession.fromValue(stream.readOctetI() >> 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(mh6pdu.intValue() << 1 | rqos.intValue());
    }

    public static class EMultiHomedIPv6PduSession extends ProtocolEnum {
        public static final EMultiHomedIPv6PduSession NOT_SUPPORTED
                = new EMultiHomedIPv6PduSession(0b0, "Multi-homed IPv6 PDU session not supported");
        public static final EMultiHomedIPv6PduSession SUPPORTED
                = new EMultiHomedIPv6PduSession(0b1, "Multi-homed IPv6 PDU session supported");

        private EMultiHomedIPv6PduSession(int value, String name) {
            super(value, name);
        }

        public static EMultiHomedIPv6PduSession fromValue(int value) {
            return fromValueGeneric(EMultiHomedIPv6PduSession.class, value);
        }
    }
}
