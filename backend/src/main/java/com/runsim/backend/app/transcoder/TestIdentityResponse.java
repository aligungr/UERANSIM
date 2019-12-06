package com.runsim.backend.app.transcoder;

import com.runsim.backend.app.TranscoderTesting;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.ies.IEImeiMobileIdentity;
import com.runsim.backend.nas.impl.messages.IdentityResponse;

public class TestIdentityResponse extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e005c00081b00000000000010";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, IdentityResponse.class);
        var mes = (IdentityResponse) message;
        assertEquals(mes.messageType, EMessageType.IDENTITY_RESPONSE);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        var imei = (IEImeiMobileIdentity) mes.mobileIdentity;
        assertInstance(mes.mobileIdentity, IEImeiMobileIdentity.class);
        assertEquals(imei.imei, "100000000000001");
    }

    @Override
    public NasMessage getMessage() {
        var mes = new IdentityResponse();
        mes.messageType = EMessageType.IDENTITY_RESPONSE;
        mes.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        ;
        mes.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;

        var imei = new IEImeiMobileIdentity();
        imei.imei = "100000000000001";
        mes.mobileIdentity = imei;
        return mes;
    }
}
