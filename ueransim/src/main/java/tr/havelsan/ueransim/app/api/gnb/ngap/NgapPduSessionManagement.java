package tr.havelsan.ueransim.app.api.gnb.ngap;

import tr.havelsan.ueransim.app.api.gnb.GNodeB;
import tr.havelsan.ueransim.app.api.gnb.rrc.RrcPduSessionManagement;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.events.ue.UeDownlinkNasEvent;
import tr.havelsan.ueransim.ngap0.NgapDataUnitType;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.core.NGAP_OctetString;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UP_TNLInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseMisc;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DataForwardingNotPossible;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PDUSessionType;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_NetworkInstance;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceFailedToSetupListSURes;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceSetupListSUReq;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceSetupListSURes;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowSetupRequestList;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_PDUSessionResourceSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_PDUSessionResourceSetupResponse;
import tr.havelsan.ueransim.app.structs.PduSessionResource;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;

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


                var res = new NGAP_PDUSessionResourceSetupItemSURes();
                res.pDUSessionID = item.pDUSessionID;
                res.pDUSessionResourceSetupResponseTransfer = new NGAP_OctetString(NgapEncoding.encodeAper(tr, NgapDataUnitType.PDUSessionResourceSetupRequestTransfer));
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

        Logging.funcOut();
    }
}
