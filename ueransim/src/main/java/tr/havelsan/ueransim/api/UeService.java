package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.messages.ServiceAccept;
import tr.havelsan.ueransim.nas.impl.messages.ServiceReject;

public class UeService {

    public static void handleServiceAccept(SimulationContext ctx, ServiceAccept message) {
        if (message.eapMessage != null) {
            UeAuthentication.handleEapMessage(ctx, message.eapMessage.eap);
        }
    }

    public static void handleServiceReject(SimulationContext ctx, ServiceReject message) {
        if (message.eapMessage != null) {
            UeAuthentication.handleEapMessage(ctx, message.eapMessage.eap);
        }
    }
}
