package tr.havelsan.ueransim.app.ue;

import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.app.Json;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;

public class FlowUtils {

    public static void logReceivedMessage(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Received NGAP PDU:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }

    public static void logNasMessageWillSend(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, nasMessage.getClass().getSimpleName() + " will be sent");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
    }

    public static void logNgapMessageWillSend(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Following NGAP message will be sent:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }

    public static void logMessageSent() {
        Console.println(Color.BLUE, "Message sent");
    }
}
