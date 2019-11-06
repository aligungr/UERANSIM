package com.runsim.backend.demo;

import com.runsim.backend.protocols.bits.Bit3;
import com.runsim.backend.protocols.nas.NasDecoder;
import com.runsim.backend.protocols.nas.impl.enums.*;
import com.runsim.backend.protocols.nas.impl.messages.RegistrationRequest;
import com.runsim.backend.protocols.nas.messages.NasMessage;
import com.runsim.backend.utils.Utils;

abstract class PduTest {
    public abstract String getPdu();

    public abstract void compare(NasMessage message);

    protected final void assertInstance(Object obj, Class<?> cls) {
        if (!cls.isAssignableFrom(obj.getClass()))
            throw new RuntimeException("assertInstance");
    }

    protected final void assertEquals(Object a, Object b) {
        if (!a.equals(b))
            throw new RuntimeException("assertEquals");
    }

    protected final void assertNotNull(Object a) {
        if (a == null)
            throw new RuntimeException("assertNotNull");
    }

}

class Test1 extends PduTest {

    @Override
    public String getPdu() {
        return "7e004171000d010011000000000000000000f12e0480808080";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, RegistrationRequest.class);
        var req = (RegistrationRequest) message;
        assertEquals(req.messageType, EMessageType.REGISTRATION_REQUEST);
        assertEquals(req.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(req.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        assertNotNull(req.mobileIdentity);
        assertNotNull(req.registrationType);
        assertNotNull(req.ueSecurityCapability);
        assertNotNull(req.nasKeySetIdentifier);

        assertEquals(req.registrationType.followOnRequestPending, EFollowOnRequest.NO_FOR_PENDING);
        assertEquals(req.registrationType.registrationType, ERegistrationType.INITIAL_REGISTRATION);

        assertEquals(req.nasKeySetIdentifier.tsc, ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT);
        assertEquals(req.nasKeySetIdentifier.nasKeySetIdentifier, new Bit3(7));

        // todo: mobile identity, ueSecurityCapability
    }
}

public class DecoderTesting {

    public static void main(String[] args) {
        var instances = new PduTest[]{
                new Test1(),
        };

        for (PduTest test : instances) {
            var data = Utils.hexStringToByteArray(test.getPdu());
            var pdu = new NasDecoder(data).decodeNAS();
            test.compare(pdu);
        }
    }
}
