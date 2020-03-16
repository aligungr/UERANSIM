package tr.havelsan.ueransim.sim.ue;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.BitStringValue;
import fr.marben.asnsdk.japi.spe.ContainingOctetStringValue;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.nas.EapDecoder;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProcedureCode;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProtocolIE_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceSetupResponse;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.ngap2.*;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet4;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static tr.havelsan.ueransim.ngap.Values.*;

public class UeUtils {

    public static Eap decodeEapFromBase64(String base64) {
        var hex = new String(Base64.getDecoder().decode((base64)));
        var bytes = Utils.hexStringToByteArray(hex);
        return EapDecoder.eapPdu((new OctetInputStream((bytes))));
    }

    public static NasMessage getNasMessage(DownlinkNASTransport message) {
        var protocolIEs =
                (List<DownlinkNASTransport.ProtocolIEs.SEQUENCE>) message.protocolIEs.valueList;

        NAS_PDU nasPayload = null;
        for (var protocolIE : protocolIEs) {
            if (protocolIE.value.getDecodedValue() instanceof NAS_PDU) {
                nasPayload = (NAS_PDU) protocolIE.value.getDecodedValue();
                break;
            }
        }

        if (nasPayload == null) return null;
        return NasDecoder.nasPdu(nasPayload.getValue());
    }

    private static GlobalRANNodeID createGlobalGnbId(int globalGnbId, VPlmn gnbPlmn) {
        try {
            var res = new GlobalGNB_ID();
            res.gNB_ID =
                    new GNB_ID(
                            GNB_ID.ASN_gNB_ID, new BitStringValue(new Octet4(globalGnbId).toByteArray(true), 32));
            res.pLMNIdentity = Ngap.plmnEncode(gnbPlmn);
            return new GlobalRANNodeID(GlobalRANNodeID.ASN_globalGNB_ID, res);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }

    private static SliceSupportList createSliceSupportList(IESNssai[] taiSliceSupportNssais) {
        var list = new ArrayList<SliceSupportItem>();

        if (taiSliceSupportNssais != null) {
            for (var nssai : taiSliceSupportNssais) {
                var item = new SliceSupportItem();
                item.s_NSSAI = new S_NSSAI();
                item.s_NSSAI.sD = new SD(nssai.sd.value.toByteArray());
                item.s_NSSAI.sST = new SST(nssai.sst.value.toByteArray());
                list.add(item);
            }
        }

        var res = new SliceSupportList();
        res.valueList = list;
        return res;
    }

    private static BroadcastPLMNList createBroadcastPlmnList(
            SupportedTA.BroadcastPlmn[] broadcastPlmns) {
        var list = new ArrayList<BroadcastPLMNItem>();

        for (var broadcastPlmn : broadcastPlmns) {
            var item = new BroadcastPLMNItem();
            item.pLMNIdentity = Ngap.plmnEncode(broadcastPlmn.plmn);
            item.tAISliceSupportList = createSliceSupportList(broadcastPlmn.taiSliceSupportNssais);
            list.add(item);
        }

        var res = new BroadcastPLMNList();
        res.valueList = list;
        return res;
    }

    private static SupportedTAList createSupportedTAList(SupportedTA[] supportedTAs) {
        var list = new ArrayList<SupportedTAItem>();

        for (var supportedTa : supportedTAs) {
            var supportedTaiItem = new SupportedTAItem();
            supportedTaiItem.tAC = new TAC(supportedTa.tac.toByteArray());
            supportedTaiItem.broadcastPLMNList = createBroadcastPlmnList(supportedTa.broadcastPlmns);
            list.add(supportedTaiItem);
        }

        var res = new SupportedTAList();
        res.valueList = list;
        return res;
    }

    public static NGAP_PDU createNgSetupRequest(int globalGnbId, VPlmn gnbPlmn, SupportedTA[] supportedTAs) {
        var ngap = new NgapBuilder()
                .withDescription(NgapPduDescription.INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                .addProtocolIE(createGlobalGnbId(globalGnbId, gnbPlmn), NgapCriticality.REJECT)
                .addProtocolIE(createSupportedTAList(supportedTAs), NgapCriticality.REJECT)
                .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, NGAP_Constants__id_DefaultPagingDRX)
                .build();
        return ngap;
    }

    public static NGAP_PDU createNGAPSuccesfullOutCome() {

        GTPTunnel gtpTunnel = new GTPTunnel();

        gtpTunnel.transportLayerAddress =
                new TransportLayerAddress(new byte[]{104, 116, 116, 112}, 32);
        gtpTunnel.gTP_TEID = new GTP_TEID(new byte[]{0, 0, 0, 2});

        PDUSessionResourceSetupResponseTransfer transfer =
                new PDUSessionResourceSetupResponseTransfer();
        transfer.qosFlowPerTNLInformation = new QosFlowPerTNLInformation();
        try {
            transfer.qosFlowPerTNLInformation.uPTransportLayerInformation =
                    new UPTransportLayerInformation(UPTransportLayerInformation.ASN_gTPTunnel, gtpTunnel);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        transfer.qosFlowPerTNLInformation.associatedQosFlowList = new AssociatedQosFlowList();

        var qosFlowIdentifier = new QosFlowIdentifier(1);
        var associatedFlowItem = new AssociatedQosFlowItem();
        associatedFlowItem.qosFlowIdentifier = qosFlowIdentifier;

        transfer.qosFlowPerTNLInformation.associatedQosFlowList.valueList =
                Collections.singletonList(associatedFlowItem);

        PDUSessionResourceSetupListSURes pduSessionResourceSetupListSURes =
                new PDUSessionResourceSetupListSURes();
        PDUSessionResourceSetupItemSURes pduSessionResourceSetupItemSURes =
                new PDUSessionResourceSetupItemSURes();

        pduSessionResourceSetupItemSURes.pDUSessionID = new PDUSessionID(8);
        pduSessionResourceSetupItemSURes.pDUSessionResourceSetupResponseTransfer =
                new ContainingOctetStringValue(transfer);

        pduSessionResourceSetupListSURes.valueList =
                Collections.singletonList(pduSessionResourceSetupItemSURes);

        var protocolIE1 = new PDUSessionResourceSetupResponse.ProtocolIEs.SEQUENCE();
        protocolIE1.id = new ProtocolIE_ID(NGAP_Constants__id_RAN_UE_NGAP_ID);
        protocolIE1.value = new OpenTypeValue(new RAN_UE_NGAP_ID(1000));
        protocolIE1.criticality = new Criticality(Criticality.ASN_ignore);

        var protocolIE2 = new PDUSessionResourceSetupResponse.ProtocolIEs.SEQUENCE();
        protocolIE2.id = new ProtocolIE_ID(NGAP_Constants__id_AMF_UE_NGAP_ID);
        protocolIE2.value = new OpenTypeValue(new AMF_UE_NGAP_ID(1));
        protocolIE2.criticality = new Criticality(Criticality.ASN_ignore);

        var protocolIE3 = new PDUSessionResourceSetupResponse.ProtocolIEs.SEQUENCE();
        protocolIE3.id = new ProtocolIE_ID(NGAP_Constants__id_PDUSessionResourceSetupListSURes);
        protocolIE3.criticality = new Criticality(Criticality.ASN_ignore);
        protocolIE3.value = new OpenTypeValue(pduSessionResourceSetupListSURes);

        var pduSessionResourceSetupResponse = new PDUSessionResourceSetupResponse();
        pduSessionResourceSetupResponse.protocolIEs = new PDUSessionResourceSetupResponse.ProtocolIEs();
        pduSessionResourceSetupResponse.protocolIEs.valueList =
                asList(protocolIE1, protocolIE2, protocolIE3);

        SuccessfulOutcome successfulOutcome = new SuccessfulOutcome();
        successfulOutcome.procedureCode = new ProcedureCode(NGAP_Constants__id_PDUSessionResourceSetup);
        successfulOutcome.criticality = new Criticality(Criticality.ASN_reject);
        successfulOutcome.value = new OpenTypeValue(pduSessionResourceSetupResponse);

        try {
            return new NGAP_PDU(NGAP_PDU.ASN_successfulOutcome, successfulOutcome);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }
}
