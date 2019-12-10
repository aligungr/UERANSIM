package tr.havelsan.ueransim.app.transcoder;

import tr.havelsan.ueransim.app.TranscoderTesting;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsIdentityType;
import tr.havelsan.ueransim.nas.impl.messages.IdentityRequest;

public class TestIdentityRequest extends TranscoderTesting.PduTest {

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

        assertEquals(mes.identityType.value, EIdentityType.IMEI);
    }

    @Override
    public NasMessage getMessage() {
        var mes = new IdentityRequest();
        mes.messageType = EMessageType.IDENTITY_REQUEST;
        mes.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        mes.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        mes.identityType = new IE5gsIdentityType();
        mes.identityType.value = EIdentityType.IMEI;
        return mes;
    }
}
