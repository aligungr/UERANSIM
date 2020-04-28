package tr.havelsan.ueransim;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.BitStringValue;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import fr.marben.asnsdk.japi.spe.SequenceValue;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialUEMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UplinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.UnsuccessfulOutcome;
import tr.havelsan.ueransim.ngap2.*;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.octets.Octet4;

import java.util.ArrayList;
import java.util.List;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_DefaultPagingDRX;

public class URSimUtils {

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
        return new NgapBuilder()
                .withDescription(NgapPduDescription.INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                .addProtocolIE(createGlobalGnbId(globalGnbId, gnbPlmn), NgapCriticality.REJECT)
                .addProtocolIE(createSupportedTAList(supportedTAs), NgapCriticality.REJECT)
                .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, NGAP_Constants__id_DefaultPagingDRX)
                .build();
    }

    public static NasMessage extractNasMessage(NGAP_PDU ngapPdu) {
        if (ngapPdu == null) return null;

        if (ngapPdu.getFieldNumber() != NGAP_PDU.ASN_initiatingMessage)
            return null;

        var initiatingMessage = (InitiatingMessage) ngapPdu.getValue();
        var procedureValue = initiatingMessage.value.getDecodedValue();

        if (procedureValue instanceof InitialUEMessage) {
            return extractNasMessage((InitialUEMessage) procedureValue);
        }
        if (procedureValue instanceof DownlinkNASTransport) {
            return extractNasMessage((DownlinkNASTransport) procedureValue);
        }
        if (procedureValue instanceof UplinkNASTransport) {
            return extractNasMessage((UplinkNASTransport) procedureValue);
        }
        return null;
    }

    public static NasMessage extractNasMessage(DownlinkNASTransport message) {
        NAS_PDU nasPayload = null;
        for (var protocolIE : (List<DownlinkNASTransport.ProtocolIEs.SEQUENCE>) message.protocolIEs.valueList) {
            if (protocolIE.value.getDecodedValue() instanceof NAS_PDU) {
                if (nasPayload != null) {
                    Console.println(Color.RED, "Multiple NAS_PDU found in NGAP_PDU. NAS_PDU is ignored.");
                    return null;
                }
                nasPayload = (NAS_PDU) protocolIE.value.getDecodedValue();
            }
        }
        return nasPayload == null ? null : NasDecoder.nasPdu(nasPayload.getValue());
    }

    public static NasMessage extractNasMessage(UplinkNASTransport message) {
        NAS_PDU nasPayload = null;
        for (var protocolIE : (List<UplinkNASTransport.ProtocolIEs.SEQUENCE>) message.protocolIEs.valueList) {
            if (protocolIE.value.getDecodedValue() instanceof NAS_PDU) {
                if (nasPayload != null) {
                    Console.println(Color.RED, "Multiple NAS_PDU found in NGAP_PDU. NAS_PDU is ignored.");
                    return null;
                }
                nasPayload = (NAS_PDU) protocolIE.value.getDecodedValue();
            }
        }
        return nasPayload == null ? null : NasDecoder.nasPdu(nasPayload.getValue());
    }

    public static NasMessage extractNasMessage(InitialUEMessage message) {
        NAS_PDU nasPayload = null;
        for (var protocolIE : (List<InitialUEMessage.ProtocolIEs.SEQUENCE>) message.protocolIEs.valueList) {
            if (protocolIE.value.getDecodedValue() instanceof NAS_PDU) {
                if (nasPayload != null) {
                    Console.println(Color.RED, "Multiple NAS_PDU found in NGAP_PDU. NAS_PDU is ignored.");
                    return null;
                }
                nasPayload = (NAS_PDU) protocolIE.value.getDecodedValue();
            }
        }
        return nasPayload == null ? null : NasDecoder.nasPdu(nasPayload.getValue());
    }

    public static SequenceValue extractNgapMessage(NGAP_PDU ngapPdu) {
        if (ngapPdu == null) {
            return null;
        }

        OpenTypeValue otv = null;

        if (ngapPdu.getFieldNumber() == NGAP_PDU.ASN_initiatingMessage) {
            var initiatingMessage = (InitiatingMessage) ngapPdu.getValue();
            otv = initiatingMessage.value;
        } else if (ngapPdu.getFieldNumber() == NGAP_PDU.ASN_successfulOutcome) {
            var successfulOutcome = (SuccessfulOutcome) ngapPdu.getValue();
            otv = successfulOutcome.value;
        } else if (ngapPdu.getFieldNumber() == NGAP_PDU.ASN_unsuccessfulOutcome) {
            var unsuccessfulOutcome = (UnsuccessfulOutcome) ngapPdu.getValue();
            otv = unsuccessfulOutcome.value;
        }

        if (otv == null) {
            return null;
        }

        return (SequenceValue) otv.getDecodedValue();
    }
}