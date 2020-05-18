package tr.havelsan.ueransim;

import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;

public class FlowLogging {

    public static void logReceivedMessage(IncomingMessage incomingMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, "Received NGAP: %s", incomingMessage.ngapMessage.getClass().getSimpleName());
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(incomingMessage.ngapPdu)));
        if (incomingMessage.nasMessage != null) {
            Console.println(Color.BLUE, "Received NAS: %s", incomingMessage.nasMessage.getClass().getSimpleName());
            Console.println(Color.WHITE_BRIGHT, Json.toJson(incomingMessage.nasMessage));
        }
    }

    public static void logSentMessage(OutgoingMessage message) {
        Console.printDiv();
        Console.println(Color.BLUE, "Sent NGAP: %s", NgapInternal.extractNgapMessage(message.ngapPdu).getClass().getSimpleName());
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(message.ngapPdu)));

        if (message.plainNas != null) {
            Console.println(Color.BLUE, "Sent Plain NAS: %s", message.plainNas.getClass().getSimpleName());
            Console.println(Color.WHITE_BRIGHT, Json.toJson(message.plainNas));
        }
        if (message.securedNas != null && message.plainNas != message.securedNas) {
            Console.println(Color.BLUE, "Sent Secured NAS: %s", message.securedNas.getClass().getSimpleName());
            Console.println(Color.WHITE_BRIGHT, Json.toJson(message.securedNas));
        }
    }

    public static void logUnhandledMessage(String receivedMessageName, Class<?>... expectedType) {
        if (expectedType == null || expectedType.length == 0) {
            Console.println(Color.YELLOW, "Unhandled message received: %s", receivedMessageName);
        } else {
            var sb = new StringBuilder();
            for (int i = 0; i < expectedType.length; i++) {
                sb.append(expectedType[i].getSimpleName());
                if (i != expectedType.length - 1) {
                    sb.append(',');
                }
            }

            var expectedMessages = sb.toString();
            Console.println(Color.YELLOW, "Unhandled message received: %s, expected messages were: %s", receivedMessageName, expectedMessages);
        }
    }

    public static void logUnhandledMessage(IncomingMessage message, Class<?>... expectedType) {
        var incomingMessage = message.ngapMessage.getClass().getSimpleName();
        if (message.nasMessage != null) {
            incomingMessage += "/" + message.nasMessage.getClass().getSimpleName();
        }
        logUnhandledMessage(incomingMessage, expectedType);
    }

    public static void logUnhandledMessage(NasMessage message, Class<?>... expectedType) {
        logUnhandledMessage(message.getClass().getSimpleName(), expectedType);
    }

    public static void logFlowComplete(BaseFlow flow) {
        Console.println(Color.GREEN_BOLD, "%s completed", flow.getClass().getSimpleName());
    }

    public static void logFlowFailed(BaseFlow flow, String errorMessage) {
        if (errorMessage != null && errorMessage.length() > 0) {
            Console.println(Color.RED_BOLD, "%s failed: %s", flow.getClass().getSimpleName(), errorMessage);
        } else {
            Console.println(Color.RED_BOLD, "%s failed", flow.getClass().getSimpleName());
        }
    }
}
