package tr.havelsan.ueransim.structs;

import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UP_TNLInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DataForwardingNotPossible;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PDUSessionType;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_NetworkInstance;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionAggregateMaximumBitRate;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowSetupRequestItem;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SecurityIndication;

import java.util.List;

public class PduSessionResource {
    public int pduSessionId;

    public NGAP_PDUSessionAggregateMaximumBitRate aggregateMaximumBitRate;
    public NGAP_UP_TNLInformation tnlInfo;
    public NGAP_DataForwardingNotPossible dataForwardingNotPossible;
    public NGAP_PDUSessionType type;
    public NGAP_SecurityIndication securityIndication;
    public NGAP_NetworkInstance networkInstance;
    public List<NGAP_QosFlowSetupRequestItem> qosFlows;
}
