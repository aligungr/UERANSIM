package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.OutgoingMessage;

public interface IMessageListener {
    void onReceive(IncomingMessage incomingMessage);

    void onSent(OutgoingMessage outgoingMessage);
}
