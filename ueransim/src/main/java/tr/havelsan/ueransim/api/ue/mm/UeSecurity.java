/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api.ue.mm;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.core.SimulationContext;
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
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.structs.SelectedAlgorithms;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit;

public class UeSecurity {

    public static void handleSecurityModeCommand(SimulationContext ctx, SecurityModeCommand message) {
        Logging.funcIn("Handling: Security Mode Command");

        // todo: check the integriti with new security context
        {
            var mac = message._macForNewSC;
        }

        // Check replayed UE security capabilities
        {
            var replayed = message.replayedUeSecurityCapabilities;
            var real = createSecurityCapabilityIe();
            if (!compareSecurityCapabilities(real, replayed)) {
                Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE),
                        new SecurityModeReject(EMmCause.UE_SECURITY_CAP_MISMATCH)));
                Logging.error(Tag.PROC, "UE Replayed Security Capability Mismatch.");
                return;
            }
        }

        // Handle EAP-Success message if any.
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.SUCCESS)) {
                UeAuthentication.handleEapSuccessMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "EAP message with code %s received in Security Mode Command. Ignoring EAP message.");
            }
        }

        // Assign selected algorithms to security context, and derive NAS keys
        ctx.nonCurrentNsc.selectedAlgorithms = new SelectedAlgorithms(
                message.selectedNasSecurityAlgorithms.typeOfIntegrityProtectionAlgorithm,
                message.selectedNasSecurityAlgorithms.typeOfCipheringAlgorithm
        );
        UeKeyManagement.deriveNasKeys(ctx.nonCurrentNsc);

        Logging.debug(Tag.VALUE, "kNasEnc: %s", ctx.nonCurrentNsc.keys.kNasEnc);
        Logging.debug(Tag.VALUE, "kNasInt: %s", ctx.nonCurrentNsc.keys.kNasInt);
        Logging.debug(Tag.VALUE, "selectedIntAlg: %s", ctx.nonCurrentNsc.selectedAlgorithms.integrity);
        Logging.debug(Tag.VALUE, "selectedEncAlg: %s", ctx.nonCurrentNsc.selectedAlgorithms.ciphering);

        // Set non-current NAS Security Context as current one.
        ctx.currentNsc = ctx.nonCurrentNsc.deepCopy();

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

        Logging.funcOut();
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
