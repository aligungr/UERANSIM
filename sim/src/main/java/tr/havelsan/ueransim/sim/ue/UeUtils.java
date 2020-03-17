package tr.havelsan.ueransim.sim.ue;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_DefaultPagingDRX;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.IGNORE;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.REJECT;
import static tr.havelsan.ueransim.ngap2.NgapPduDescription.INITIATING_MESSAGE;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.BitStringValue;
import java.util.ArrayList;
import java.util.List;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap.ngap_ies.BroadcastPLMNItem;
import tr.havelsan.ueransim.ngap.ngap_ies.BroadcastPLMNList;
import tr.havelsan.ueransim.ngap.ngap_ies.GNB_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.GlobalGNB_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.GlobalRANNodeID;
import tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU;
import tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX;
import tr.havelsan.ueransim.ngap.ngap_ies.SD;
import tr.havelsan.ueransim.ngap.ngap_ies.SST;
import tr.havelsan.ueransim.ngap.ngap_ies.S_NSSAI;
import tr.havelsan.ueransim.ngap.ngap_ies.SliceSupportItem;
import tr.havelsan.ueransim.ngap.ngap_ies.SliceSupportList;
import tr.havelsan.ueransim.ngap.ngap_ies.SupportedTAItem;
import tr.havelsan.ueransim.ngap.ngap_ies.SupportedTAList;
import tr.havelsan.ueransim.ngap.ngap_ies.TAC;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.ngap2.SupportedTA;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class UeUtils {

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

      if (nasPayload == null) {
          return null;
      }
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


  public static NGAP_PDU createNgSetupRequest(int globalGnbId, VPlmn gnbPlmn,
      SupportedTA[] supportedTAs) {
    return new NgapBuilder()
        .withDescription(INITIATING_MESSAGE)
        .withProcedure(NgapProcedure.NGSetupRequest, REJECT)
        .addProtocolIE(createGlobalGnbId(globalGnbId, gnbPlmn), REJECT)
        .addProtocolIE(createSupportedTAList(supportedTAs), REJECT)
        .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), IGNORE,
            NGAP_Constants__id_DefaultPagingDRX)
        .build();
  }
}
