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

import tr.havelsan.ueransim.app.api.gnb.GNodeB;
import tr.havelsan.ueransim.app.api.gnb.rrc.RrcPduSessionManagement;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.events.ue.UeDownlinkNasEvent;
import tr.havelsan.ueransim.app.structs.PduSessionResource;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.ngap0.NgapDataUnitType;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.core.NGAP_OctetString;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UP_TNLInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseMisc;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DataForwardingNotPossible;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PDUSessionType;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_NetworkInstance;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_GTP_TEID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_PDUSessionResourceSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_PDUSessionResourceSetupResponse;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Logging;

public class NgapPduSessionManagement {

    public static void receiveResourceSetupRequest(GnbSimContext ctx, NGAP_PDUSessionResourceSetupRequest message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling PDU Session Resource Setup Request");

        var response = new NGAP_PDUSessionResourceSetupResponse();
        var successList = new NGAP_PDUSessionResourceSetupListSURes();
        var failedList = new NGAP_PDUSessionResourceFailedToSetupListSURes();

        var associatedUe = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeIdDefault(ctx, message));

        var list = message.getProtocolIe(NGAP_PDUSessionResourceSetupListSUReq.class);

        for (var item : list.list) {

            var transfer = (NGAP_PDUSessionResourceSetupRequestTransfer)
                    NgapEncoding.decodeAper(item.pDUSessionResourceSetupRequestTransfer.value,
                            NgapDataUnitType.PDUSessionResourceSetupRequestTransfer);

            var resource = new PduSessionResource();
            resource.pduSessionId = (int) item.pDUSessionID.value;

            for (var ie : transfer.protocolIEs.list) {
                var value = ie.value.getPresentValue();
                if (value instanceof NGAP_PDUSessionAggregateMaximumBitRate) {
                    resource.aggregateMaximumBitRate = (NGAP_PDUSessionAggregateMaximumBitRate) value;
                }
                if (value instanceof NGAP_UP_TNLInformation) {
                    resource.tnlInfo = (NGAP_UP_TNLInformation) value;
                }
                if (value instanceof NGAP_DataForwardingNotPossible) {
                    resource.dataForwardingNotPossible = (NGAP_DataForwardingNotPossible) value;
                }
                if (value instanceof NGAP_PDUSessionType) {
                    resource.type = (NGAP_PDUSessionType) value;
                }
                if (value instanceof NGAP_SecurityIndication) {
                    resource.securityIndication = (NGAP_SecurityIndication) value;
                }
                if (value instanceof NGAP_NetworkInstance) {
                    resource.networkInstance = (NGAP_NetworkInstance) value;
                }
                if (value instanceof NGAP_QosFlowSetupRequestList) {
                    resource.qosFlows = ((NGAP_QosFlowSetupRequestList) value).list;
                }
            }

            if (RrcPduSessionManagement.pduResourceSetup(ctx, associatedUe, resource)) {
                if (item.pDUSessionNAS_PDU != null) {
                    Simulation.pushUeEvent(ctx.simCtx, associatedUe.ueCtxId, new UeDownlinkNasEvent(item.pDUSessionNAS_PDU.value));
                }

                var tr = new NGAP_PDUSessionResourceSetupResponseTransfer();
                tr.qosFlowPerTNLInformation = new NGAP_QosFlowPerTNLInformation();
                tr.qosFlowPerTNLInformation.associatedQosFlowList = new NGAP_AssociatedQosFlowList();

                for (var qosFlow : resource.qosFlows) {
                    var associatedQosFlowItem = new NGAP_AssociatedQosFlowItem();
                    associatedQosFlowItem.qosFlowIdentifier = qosFlow.qosFlowIdentifier;

                    tr.qosFlowPerTNLInformation.associatedQosFlowList.list.add(associatedQosFlowItem);
                }

                tr.qosFlowPerTNLInformation.uPTransportLayerInformation = new NGAP_UPTransportLayerInformation();
                tr.qosFlowPerTNLInformation.uPTransportLayerInformation.gTPTunnel = new NGAP_GTPTunnel();
                tr.qosFlowPerTNLInformation.uPTransportLayerInformation.gTPTunnel.gTP_TEID = new NGAP_GTP_TEID("00000001"); // TODO
                tr.qosFlowPerTNLInformation.uPTransportLayerInformation.gTPTunnel.transportLayerAddress = new NGAP_TransportLayerAddress(Utils.getLocalAddress());

                var res = new NGAP_PDUSessionResourceSetupItemSURes();
                res.pDUSessionID = item.pDUSessionID;
                res.pDUSessionResourceSetupResponseTransfer = new NGAP_OctetString(NgapEncoding.encodeAper(tr, NgapDataUnitType.PDUSessionResourceSetupResponseTransfer));
                successList.list.add(res);
            } else {
                var tr = new NGAP_PDUSessionResourceSetupUnsuccessfulTransfer();
                tr.cause = new NGAP_Cause();
                tr.cause.misc = NGAP_CauseMisc.UNSPECIFIED;

                var res = new NGAP_PDUSessionResourceFailedToSetupItemSURes();
                res.pDUSessionID = item.pDUSessionID;
                res.pDUSessionResourceSetupUnsuccessfulTransfer = new NGAP_OctetString(NgapEncoding.encodeAper(tr, NgapDataUnitType.PDUSessionResourceSetupUnsuccessfulTransfer));
                failedList.list.add(res);
            }
        }

        //var pagingPriority = message.getProtocolIe(NGAP_RANPagingPriority.class);

        var nasPdu = message.getProtocolIe(NGAP_NAS_PDU.class);
        if (nasPdu != null) {
            Simulation.pushUeEvent(ctx.simCtx, associatedUe.ueCtxId, new UeDownlinkNasEvent(nasPdu.value));
        }

        if (!successList.list.isEmpty()) {
            response.addProtocolIe(successList);
        }
        if (!failedList.list.isEmpty()) {
            response.addProtocolIe(failedList);
        }

        GNodeB.sendNgapUeAssociated(ctx, associatedUe.ueCtxId, response);

        Logging.success(Tag.PROCEDURE_RESULT, "PDU Session Establishment is successful");

        Logging.funcOut();
    }
}
