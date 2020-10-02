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
 */

package tr.havelsan.ueransim.app.api.ue.sm;

import tr.havelsan.ueransim.app.structs.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentAccept;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentReject;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentRequest;
import tr.havelsan.ueransim.utils.Tag;

class SmPduSessionEstablishment {

    public static void sendEstablishmentRequest(UeSimContext ctx) {
        ctx.logger.funcIn("Sending PDU Session Establishment Request");

        var pduSessionId = SmPduSessionManagement.allocatePduSessionId(ctx);
        if (pduSessionId == null) {
            ctx.logger.error(Tag.PROC, "PDU Session Establishment Request could not send");
            ctx.logger.funcOut();
            return;
        }

        var procedureTransactionId = SmPduSessionManagement.allocateProcedureTransactionId(ctx);
        if (procedureTransactionId == null) {
            ctx.logger.error(Tag.PROC, "PDU Session Establishment Request could not send");
            SmPduSessionManagement.releasePduSession(ctx, pduSessionId);
            ctx.logger.funcOut();
            return;
        }

        var pduSessionEstablishmentRequest = new PduSessionEstablishmentRequest();
        pduSessionEstablishmentRequest.pduSessionId = pduSessionId;
        pduSessionEstablishmentRequest.pti = procedureTransactionId;
        pduSessionEstablishmentRequest.integrityProtectionMaximumDataRate =
                new IEIntegrityProtectionMaximumDataRate(
                        IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.FULL_DATA_RATE,
                        IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.FULL_DATA_RATE);
        pduSessionEstablishmentRequest.pduSessionType = new IEPduSessionType(EPduSessionType.IPV4);
        pduSessionEstablishmentRequest.sscMode = new IESscMode(IESscMode.ESscMode.SSC_MODE_1);

        ctx.ueTimers.t3580.start();

        SessionManagement.sendSm(ctx, pduSessionId, pduSessionEstablishmentRequest);

        ctx.logger.funcOut();
    }

    public static void receiveEstablishmentAccept(UeSimContext ctx, PduSessionEstablishmentAccept message) {
        ctx.logger.funcIn("Handling: PDU Session Establishment Accept");

        if (message.smCause != null) {
            ctx.logger.warning(Tag.PROC, "SM cause received in PduSessionEstablishmentAccept: %s", message.smCause.value);
        }

        ctx.ueTimers.t3580.stop();

        SmPduSessionManagement.releaseProcedureTransactionId(ctx, message.pti);

        var pduSession = ctx.smCtx.pduSessions[message.pduSessionId.intValue()];
        if (pduSession == null) {
            ctx.logger.error(Tag.PROC, "PDU session not found: %s", message.pduSessionId);
            ctx.logger.funcOut();
            return;
        }

        pduSession.isEstablished = true;
        pduSession.authorizedQoSRules = message.authorizedQoSRules;
        pduSession.sessionAmbr = message.sessionAmbr;
        pduSession.authorizedQoSFlowDescriptions = message.authorizedQoSFlowDescriptions;

        ctx.logger.info(Tag.PROC, "PDU session established: %s", message.pduSessionId);

        ctx.logger.funcOut();
    }

    public static void receiveEstablishmentReject(UeSimContext ctx, PduSessionEstablishmentReject message) {

    }
}
