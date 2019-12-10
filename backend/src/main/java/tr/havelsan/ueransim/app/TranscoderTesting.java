package tr.havelsan.ueransim.app;

import tr.havelsan.ueransim.app.transcoder.*;
import tr.havelsan.ueransim.app.transcoder.*;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.Utils;

public class TranscoderTesting {

    public static void main(String[] args) {
        performDecoderTest(new TestRegistrationRequest());
        performDecoderTest(new TestAuthenticationRequest());
        performDecoderTest(new TestAuthenticationResponse());
        performDecoderTest(new TestIdentityRequest());
        performDecoderTest(new TestIdentityResponse());
        performDecoderTest(new TestRegistrationComplete());
        performDecoderTest(new TestRegistrationAccept());
        performDecoderTest(new TestRegistrationReject());

        performEncoderTest(new TestRegistrationRequest());
        performEncoderTest(new TestAuthenticationRequest());
        performEncoderTest(new TestAuthenticationResponse());
        performEncoderTest(new TestIdentityRequest());
        performEncoderTest(new TestIdentityResponse());
        performEncoderTest(new TestRegistrationComplete());
        performEncoderTest(new TestRegistrationAccept());
        performEncoderTest(new TestRegistrationReject());
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
            printToCompare(Utils.insertSpaces(string, 2), Utils.insertSpaces(test.getPdu().toUpperCase(), 2));
            System.exit(1);
        }
    }

    private static void printToCompare(String s1, String s2) {
        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");
        boolean indexes[] = new boolean[Math.max(arr1.length, arr2.length)];
        for (int i = 0; i < Math.min(arr1.length, arr2.length); i++)
            indexes[i] = arr1[i].equals(arr2[i]);

        System.err.println("Encoded PDU:");
        int i = 0;
        for (var hex : arr1) {
            if (!indexes[i]) {
                System.err.print("[");
                System.err.print(hex);
                System.err.print("]");
            } else {
                System.err.print(" ");
                System.err.print(hex);
                System.err.print(" ");
            }
            System.err.print(" ");
            i++;
        }
        System.err.println();
        System.err.println("Expected PDU:");
        i = 0;
        for (var hex : arr2) {
            if (!indexes[i]) {
                System.err.print("[");
                System.err.print(hex);
                System.err.print("]");
            } else {
                System.err.print(" ");
                System.err.print(hex);
                System.err.print(" ");
            }
            System.err.print(" ");
            i++;
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
