package com.runsim.backend.demo.decoder;

import com.runsim.backend.demo.DecoderTesting;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.messages.RegistrationAccept;

public class TestRegistrationAccept extends DecoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e0042010977000bf20011002a55aa000000011505040109afaf50020000";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, RegistrationAccept.class);
        var mes = (RegistrationAccept) message;
        assertEquals(mes.messageType, EMessageType.REGISTRATION_ACCEPT);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        throw new NotImplementedException("this test not completed yet");
    }
}
