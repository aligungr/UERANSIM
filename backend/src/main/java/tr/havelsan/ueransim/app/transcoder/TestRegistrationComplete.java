package tr.havelsan.ueransim.app.transcoder;

import tr.havelsan.ueransim.app.TranscoderTesting;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationComplete;

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
        var message = new RegistrationComplete();
        message.messageType = EMessageType.REGISTRATION_COMPLETE;
        message.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        message.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        message.sorTransparentContainer = null;
        return message;
    }
}
