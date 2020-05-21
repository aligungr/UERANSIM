package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiSvMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiSvRequest;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeCommand;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeComplete;

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

        // todo: container
    }
}
