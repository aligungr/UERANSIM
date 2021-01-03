/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
import java.util.Objects;
import java.util.UUID;

public class PduSessionResource {
    public final UUID ueId;
    public final int pduSessionId;

    public NGAP_PDUSessionAggregateMaximumBitRate sessionAggregateMaximumBitRate;
    public NGAP_DataForwardingNotPossible dataForwardingNotPossible;
    public NGAP_PDUSessionType type;
    public NGAP_SecurityIndication securityIndication;
    public NGAP_NetworkInstance networkInstance;
    public NGAP_UPTransportLayerInformation upLayer;
    public NGAP_UPTransportLayerInformation downLayer;
    public List<NGAP_QosFlowSetupRequestItem> qosFlows;

    public PduSessionResource(UUID ueId, int pduSessionId) {
        this.ueId = ueId;
        this.pduSessionId = pduSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PduSessionResource that = (PduSessionResource) o;
        return pduSessionId == that.pduSessionId &&
                Objects.equals(ueId, that.ueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ueId, pduSessionId);
    }
}
