package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiSvMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiSvRequest;
import tr.havelsan.ueransim.nas.impl.ies.IENasMessageContainer;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeCommand;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeComplete;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

public class UeSecurity {


    public static void handleSecurityModeCommand(SimulationContext ctx, SecurityModeCommand message) {
        // todo: check for mandatory replayed ue security cap.

        // todo: check for mandatory ngKSI, current/noncurrent securit context,timers, counts etc

        // todo: check other optional fields

        // Assign selected algorithms to security context, and derive NAS keys
        ctx.nasSecurityContext.selectedAlgorithms.integrity
                = message.selectedNasSecurityAlgorithms.typeOfIntegrityProtectionAlgorithm;
        ctx.nasSecurityContext.selectedAlgorithms.ciphering
                = message.selectedNasSecurityAlgorithms.typeOfCipheringAlgorithm;
        UeKeyManagement.deriveNasKeys(ctx.nasSecurityContext);

        // Prepare response
        var response = new SecurityModeComplete();

        // Append IMEISV if requested
        if (message.imeiSvRequest != null && message.imeiSvRequest.imeiSvRequest.equals(IEImeiSvRequest.EImeiSvRequest.REQUESTED)) {
            // todo: imei vs imeiSv may be dist.
            response.imeiSv = new IEImeiSvMobileIdentity(ctx.ueData.imei);
        }

        response.nasMessageContainer = new IENasMessageContainer(NasEncoder.nasPdu(ctx.registrationRequest));

        // Send response
        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
    }
}
