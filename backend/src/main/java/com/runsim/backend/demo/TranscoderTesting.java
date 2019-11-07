package com.runsim.backend.demo;

import com.runsim.backend.demo.transcoder.*;
import com.runsim.backend.nas.Decoder;
import com.runsim.backend.nas.Encoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.utils.Utils;

public class TranscoderTesting {

    public static void main(String[] args) {
        performDecoderTest(new TestRegistrationRequest());
        performDecoderTest(new TestAuthenticationRequest());
        performDecoderTest(new TestAuthenticationResponse());
        // performDecoderTest(new TestAuthenticationResult()); // todo
        performDecoderTest(new TestIdentityRequest());
        performDecoderTest(new TestIdentityResponse());
        performDecoderTest(new TestRegistrationComplete());
        performDecoderTest(new TestRegistrationAccept());
        performEncoderTest(new TestRegistrationRequest());
        performEncoderTest(new TestAuthenticationRequest());
        performEncoderTest(new TestAuthenticationResponse());
        // performEncoderTest(new TestAuthenticationResult()); // todo
        performEncoderTest(new TestIdentityRequest());
        performEncoderTest(new TestIdentityResponse());
        performEncoderTest(new TestRegistrationComplete());
        performEncoderTest(new TestRegistrationAccept());
    }

    private static void performDecoderTest(PduTest test) {
        var data = Utils.hexStringToByteArray(test.getPdu());
        var pdu = Decoder.nasPdu(data);
        test.compare(pdu);
        System.out.println(test.getClass().getSimpleName() + " Decoder: OK");
    }

    private static void performEncoderTest(PduTest test) {
        var message = test.getMessage();
        var pdu = Encoder.nasPdu(message);
        var string = Utils.byteArrayToHexString(pdu);

        if (string.equals(test.getPdu()))
            System.out.println(test.getClass().getSimpleName() + " Encoder: OK");
        else
            System.err.println(test.getClass().getSimpleName() + " Encoder: FAILED");
    }

    public static abstract class PduTest {
        public abstract String getPdu();

        public abstract void compare(NasMessage message);

        public abstract NasMessage getMessage();

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
