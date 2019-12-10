package tr.havelsan.ueransim.app.transcoder;

import tr.havelsan.ueransim.app.TranscoderTesting;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gMmCause;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationReject;

public class TestRegistrationReject extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e00446f";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, RegistrationReject.class);
        var mes = (RegistrationReject) message;
        assertEquals(mes.messageType, EMessageType.REGISTRATION_REJECT);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);
        assertNull(mes.eapMessage);
        assertNull(mes.t3346value);
        assertNull(mes.t3502value);
        assertNotNull(mes.mmCause);
        assertEquals(mes.mmCause.value, EMmCause.UNSPECIFIED_PROTOCOL_ERROR);
    }

    @Override
    public NasMessage getMessage() {
        var message = new RegistrationReject();
        message.messageType = EMessageType.REGISTRATION_REJECT;
        message.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        message.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        message.mmCause = new IE5gMmCause();
        message.mmCause.value = EMmCause.UNSPECIFIED_PROTOCOL_ERROR;
        return message;
    }
}
