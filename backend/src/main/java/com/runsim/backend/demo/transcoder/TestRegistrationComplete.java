package com.runsim.backend.demo.transcoder;

import com.runsim.backend.demo.TranscoderTesting;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.messages.RegistrationComplete;

public class TestRegistrationComplete extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e0043";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, RegistrationComplete.class);
        var mes = (RegistrationComplete) message;
        assertEquals(mes.messageType, EMessageType.REGISTRATION_COMPLETE);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);
        assertNull(mes.sorTransparentContainer);
    }

    @Override
    public NasMessage getMessage() {
        throw new NotImplementedException("");
    }
}
