package com.runsim.backend.demo;

import com.runsim.backend.demo.decoder.*;
import com.runsim.backend.protocols.nas.NasDecoder;
import com.runsim.backend.protocols.nas.messages.NasMessage;
import com.runsim.backend.utils.Utils;

public class DecoderTesting {

    public static void main(String[] args) {
        performTest(new TestRegistrationRequest());
        performTest(new TestAuthenticationRequest());
        performTest(new TestAuthenticationResponse());
        performTest(new TestIdentityRequest());
        performTest(new TestIdentityResponse());
        System.out.println("All tests are successful");
    }

    private static void performTest(PduTest test) {
        System.out.println("Testing: " + test.getClass().getSimpleName());
        var data = Utils.hexStringToByteArray(test.getPdu());
        var pdu = new NasDecoder(data).decodeNAS();
        test.compare(pdu);
    }

    public static abstract class PduTest {
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

        protected final void assertNull(Object a) {
            if (a != null)
                throw new RuntimeException("assertNull");
        }
    }
}
