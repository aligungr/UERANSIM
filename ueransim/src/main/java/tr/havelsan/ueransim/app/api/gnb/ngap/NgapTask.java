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

package tr.havelsan.ueransim.app.api.gnb.ngap;

import tr.havelsan.itms.Itms;
import tr.havelsan.itms.ItmsTask;
import tr.havelsan.ueransim.app.api.gnb.GNodeB;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.exceptions.NgapErrorException;
import tr.havelsan.ueransim.app.itms.NgapReceiveMessage;
import tr.havelsan.ueransim.app.structs.Guami;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UE_NGAP_IDs;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseProtocol;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Logging;

public class NgapTask extends ItmsTask {

    private final GnbSimContext ctx;

    public NgapTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof NgapReceiveMessage) {
                receiveNgap(((NgapReceiveMessage) msg).associatedAmf, ((NgapReceiveMessage) msg).stream, ((NgapReceiveMessage) msg).ngapPdu);
            }
        }
    }

    private void receiveNgap(Guami associatedAmf, int stream, NGAP_PDU ngapPdu) {
        Logging.debug(Tag.MESSAGING, "Received NGAP: %s", ngapPdu.getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var ngapMessage = Ngap.getMessageFromPdu(ngapPdu);
        if (ngapMessage == null) {
            return;
        }

        Simulation.triggerOnReceive(ctx, ngapMessage);

        try {
            if (!ctx.config.ignoreStreamIds) {
                NGAP_Value ie = ngapMessage.getProtocolIe(NGAP_UE_NGAP_IDs.class);
                if (ie != null) {
                    if (stream == 0) {
                        Logging.error(Tag.CONNECTION, "received stream number == 0 in UE-associated signalling");
                        throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                    }
                    var ueCtx = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeForUeNgapIds(ctx, ngapMessage));
                    if (ueCtx.downlinkStream == 0) {
                        ueCtx.downlinkStream = stream;
                    } else if (ueCtx.downlinkStream != stream) {
                        Logging.error(Tag.CONNECTION, "received stream number is inconsistent. received %d, expected :%d", stream, ueCtx.downlinkStream);
                        throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                    }
                } else {
                    ie = ngapMessage.getProtocolIe(NGAP_RAN_UE_NGAP_ID.class);
                    if (ie != null) {
                        if (stream == 0) {
                            Logging.error(Tag.CONNECTION, "received stream number == 0 in UE-associated signalling");
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                        var ueCtx = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeIdDefault(ctx, ngapMessage));
                        if (ueCtx.downlinkStream == 0) {
                            ueCtx.downlinkStream = stream;
                        } else if (ueCtx.downlinkStream != stream) {
                            Logging.error(Tag.CONNECTION, "received stream number is inconsistent. received %d, expected :%d", stream, ueCtx.downlinkStream);
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                    } else {
                        if (stream != 0) {
                            Logging.error(Tag.CONNECTION, "received stream number != 0 in non-UE-associated signalling");
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                    }
                }
            }

            if (ngapMessage instanceof NGAP_NGSetupResponse) {
                NgapInterfaceManagement.receiveNgSetupResponse(ctx, (NGAP_NGSetupResponse) ngapMessage);
            } else if (ngapMessage instanceof NGAP_NGSetupFailure) {
                NgapInterfaceManagement.receiveNgSetupFailure(ctx, (NGAP_NGSetupFailure) ngapMessage);
            } else if (ngapMessage instanceof NGAP_DownlinkNASTransport) {
                NgapNasTransport.receiveDownlinkNasTransport(ctx, (NGAP_DownlinkNASTransport) ngapMessage);
            } else if (ngapMessage instanceof NGAP_InitialContextSetupRequest) {
                NgapUeContextManagement.receiveInitialContextSetup(ctx, (NGAP_InitialContextSetupRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_RerouteNASRequest) {
                NgapNasTransport.receiveRerouteNasRequest(ctx, associatedAmf, (NGAP_RerouteNASRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_UEContextReleaseCommand) {
                NgapUeContextManagement.receiveContextReleaseCommand(ctx, (NGAP_UEContextReleaseCommand) ngapMessage);
            } else if (ngapMessage instanceof NGAP_UEContextModificationRequest) {
                NgapUeContextManagement.receiveContextModificationRequest(ctx, (NGAP_UEContextModificationRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_PDUSessionResourceSetupRequest) {
                NgapPduSessionManagement.receiveResourceSetupRequest(ctx, (NGAP_PDUSessionResourceSetupRequest) ngapMessage);
            } else {
                Logging.error(Tag.MESSAGING, "Unhandled message received: %s", ngapMessage.getClass().getSimpleName());
            }
        } catch (NgapErrorException e) {
            var errorIndication = new NGAP_ErrorIndication();
            var ngapCause = new NGAP_Cause();
            ngapCause.setPresentValue(e.cause);

            errorIndication.addProtocolIe(ngapCause);
            if (e.associatedUe != null) {
                NgapTransfer.sendNgapUeAssociated(ctx, e.associatedUe, errorIndication);
            } else {
                NgapTransfer.sendNgapNonUe(ctx, associatedAmf, errorIndication);
            }
        }
    }
}
