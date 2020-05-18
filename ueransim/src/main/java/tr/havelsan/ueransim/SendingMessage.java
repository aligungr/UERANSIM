package tr.havelsan.ueransim;

import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap2.NgapBuilder;

public class SendingMessage {
    public final NgapBuilder ngapBuilder;
    public final NasMessage nasMessage;

    public SendingMessage(NgapBuilder ngapBuilder, NasMessage nasMessage) {
        this.ngapBuilder = ngapBuilder;
        this.nasMessage = nasMessage;
    }
}
