/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mm;

import tr.havelsan.ueransim.app.common.SelectedAlgorithms;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiSvMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiSvRequest;
import tr.havelsan.ueransim.nas.impl.ies.IENasMessageContainer;
import tr.havelsan.ueransim.nas.impl.ies.IEUeSecurityCapability;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeCommand;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeComplete;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeReject;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.console.Log;

public class MmSecurity {

    public static void receiveSecurityModeCommand(UeSimContext ctx, SecurityModeCommand message) {
        Log.funcIn("Handling: Security Mode Command");

        // todo: check the integriti with new security context
        {
            var mac = message._macForNewSC;
        }

        // Check replayed UE security capabilities
        {
            var replayed = message.replayedUeSecurityCapabilities;
            var real = createSecurityCapabilityIe();
            if (!compareSecurityCapabilities(real, replayed)) {
                MobilityManagement.sendMm(ctx, new SecurityModeReject(EMmCause.UE_SECURITY_CAP_MISMATCH));
                Log.error(Tag.FLOW, "UE Replayed Security Capability Mismatch.");
                return;
            }
        }

        // Handle EAP-Success message if any.
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.SUCCESS)) {
                MmAuthentication.receiveEapSuccessMessage(ctx, message.eapMessage.eap);
            } else {
                Log.warning(Tag.FLOW, "EAP message with code %s received in Security Mode Command. Ignoring EAP message.");
            }
        }

        // Assign ABBA (if any)
        if (message.abba != null) {
            ctx.nonCurrentNsCtx.keys.abba = message.abba.contents;
        }

        // Assign selected algorithms to security context, and derive NAS keys
        ctx.nonCurrentNsCtx.selectedAlgorithms = new SelectedAlgorithms(
                message.selectedNasSecurityAlgorithms.typeOfIntegrityProtectionAlgorithm,
                message.selectedNasSecurityAlgorithms.typeOfCipheringAlgorithm
        );
        MmKeyManagement.deriveNasKeys(ctx.nonCurrentNsCtx);

        Log.debug(Tag.VALUE, "kNasEnc: %s", ctx.nonCurrentNsCtx.keys.kNasEnc);
        Log.debug(Tag.VALUE, "kNasInt: %s", ctx.nonCurrentNsCtx.keys.kNasInt);
        Log.debug(Tag.VALUE, "selectedIntAlg: %s", ctx.nonCurrentNsCtx.selectedAlgorithms.integrity);
        Log.debug(Tag.VALUE, "selectedEncAlg: %s", ctx.nonCurrentNsCtx.selectedAlgorithms.ciphering);

        // Set non-current NAS Security Context as current one.
        ctx.currentNsCtx = ctx.nonCurrentNsCtx.deepCopy();

        // Prepare response
        var response = new SecurityModeComplete();

        // Append IMEISV if requested
        if (message.imeiSvRequest != null && message.imeiSvRequest.imeiSvRequest.equals(IEImeiSvRequest.EImeiSvRequest.REQUESTED)) {
            // todo: imei vs imeiSv may be dist.
            response.imeiSv = new IEImeiSvMobileIdentity(ctx.ueConfig.imei);
        }

        response.nasMessageContainer = new IENasMessageContainer(NasEncoder.nasPdu(ctx.mmCtx.registrationRequest));

        // Send response
        MobilityManagement.sendMm(ctx, response);

        Log.funcOut();
    }

    public static IEUeSecurityCapability createSecurityCapabilityIe() {
        var ie = new IEUeSecurityCapability();
        ie.supported_5G_EA0 = Bit.ONE;
        ie.supported_128_5G_EA1 = Bit.ONE;
        ie.supported_128_5G_EA2 = Bit.ONE;
        ie.supported_128_5G_EA3 = Bit.ONE;
        ie.supported_5G_EA4 = Bit.ZERO;
        ie.supported_5G_EA5 = Bit.ZERO;
        ie.supported_5G_EA6 = Bit.ZERO;
        ie.supported_5G_EA7 = Bit.ZERO;
        ie.supported_5G_IA0 = Bit.ONE;
        ie.supported_128_5G_IA1 = Bit.ONE;
        ie.supported_128_5G_IA2 = Bit.ONE;
        ie.supported_128_5G_IA3 = Bit.ONE;
        ie.supported_5G_IA4 = Bit.ZERO;
        ie.supported_5G_IA5 = Bit.ZERO;
        ie.supported_5G_IA6 = Bit.ZERO;
        ie.supported_5G_IA7 = Bit.ZERO;
        ie.supported_EEA0 = Bit.ONE;
        ie.supported_128_EEA1 = Bit.ONE;
        ie.supported_128_EEA2 = Bit.ONE;
        ie.supported_128_EEA3 = Bit.ONE;
        ie.supported_EEA4 = Bit.ZERO;
        ie.supported_EEA5 = Bit.ZERO;
        ie.supported_EEA6 = Bit.ZERO;
        ie.supported_EEA7 = Bit.ZERO;
        ie.supported_EIA0 = Bit.ONE;
        ie.supported_128_EIA1 = Bit.ONE;
        ie.supported_128_EIA2 = Bit.ONE;
        ie.supported_128_EIA3 = Bit.ONE;
        ie.supported_EIA4 = Bit.ZERO;
        ie.supported_EIA5 = Bit.ZERO;
        ie.supported_EIA6 = Bit.ZERO;
        ie.supported_EIA7 = Bit.ZERO;
        return ie;
    }

    private static boolean compareSecurityCapabilities(IEUeSecurityCapability cap1, IEUeSecurityCapability cap2) {
        var stream1 = new OctetOutputStream();
        var stream2 = new OctetOutputStream();

        NasEncoder.ie2346(stream1, cap1);
        NasEncoder.ie2346(stream2, cap2);

        return stream1.toOctetString().equals(stream2.toOctetString());
    }
}
