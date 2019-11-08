package com.runsim.backend.demo;

import com.runsim.backend.demo.transcoder.*;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.utils.OctetInputStream;
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
        var pdu = NasDecoder.nasPdu(new OctetInputStream(data));
        test.compare(pdu);
        System.out.println(test.getClass().getSimpleName() + " Decoder: OK");
    }

    private static void performEncoderTest(PduTest test) {
        var message = test.getMessage();
        var pdu = NasEncoder.nasPdu(message);
        var string = Utils.byteArrayToHexString(pdu).toUpperCase();

        if (string.equals(test.getPdu().toUpperCase()))
            System.out.println(test.getClass().getSimpleName() + " Encoder: OK");
        else {
            System.err.println(test.getClass().getSimpleName() + " Encoder: FAILED");
            System.err.println("Encoded PDU:");
            System.err.println(Utils.insertSpaces(string, 2));
            System.err.println("Expected PDU:");
            System.err.println(Utils.insertSpaces(test.getPdu().toUpperCase(), 2));
            System.exit(1);
        }
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
