package tr.havelsan.ueransim.apimm;

import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.impl.messages.ServiceAccept;
import tr.havelsan.ueransim.nas.impl.messages.ServiceReject;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class UeService {

    public static void handleServiceAccept(SimulationContext ctx, ServiceAccept message) {
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                UeAuthentication.handleEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "network sent EAP with type of %s in ServiceAccept, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }
    }

    public static void handleServiceReject(SimulationContext ctx, ServiceReject message) {
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                UeAuthentication.handleEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "network sent EAP with type of %s in ServiceReject, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }
    }
}
