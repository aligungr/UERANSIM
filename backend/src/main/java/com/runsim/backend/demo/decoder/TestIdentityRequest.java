package com.runsim.backend.demo.decoder;

import com.runsim.backend.demo.DecoderTesting;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.messages.IdentityRequest;

public class TestIdentityRequest extends DecoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e005b03";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, IdentityRequest.class);
        var mes = (IdentityRequest) message;
        assertEquals(mes.messageType, EMessageType.IDENTITY_REQUEST);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        assertEquals(mes.identityType, EIdentityType.IMEI);
    }
}
