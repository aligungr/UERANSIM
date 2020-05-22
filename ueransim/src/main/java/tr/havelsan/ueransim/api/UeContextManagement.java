package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

public class UeContextManagement {

    public static void handleInitialContextSetup(SimulationContext ctx, InitialContextSetupRequest message) {
        // todo
        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.InitialContextSetupResponse, NgapCriticality.REJECT),
                null));
    }
}
