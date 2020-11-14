/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DataForwardingNotPossible;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PDUSessionType;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_NetworkInstance;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionAggregateMaximumBitRate;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowSetupRequestItem;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SecurityIndication;

import java.util.List;
import java.util.UUID;

public class PduSessionResource {
    public UUID ueId;
    public int pduSessionId;

    public NGAP_PDUSessionAggregateMaximumBitRate aggregateMaximumBitRate;
    public NGAP_DataForwardingNotPossible dataForwardingNotPossible;
    public NGAP_PDUSessionType type;
    public NGAP_SecurityIndication securityIndication;
    public NGAP_NetworkInstance networkInstance;
    public NGAP_UPTransportLayerInformation upLayer;
    public NGAP_UPTransportLayerInformation downLayer;
    public List<NGAP_QosFlowSetupRequestItem> qosFlows;
}
