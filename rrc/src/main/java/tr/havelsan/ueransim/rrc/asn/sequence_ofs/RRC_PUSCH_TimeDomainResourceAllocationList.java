/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PUSCH_TimeDomainResourceAllocation;

public class RRC_PUSCH_TimeDomainResourceAllocationList extends RRC_SequenceOf<RRC_PUSCH_TimeDomainResourceAllocation> {

    @Override
    public String getAsnName() {
        return "PUSCH-TimeDomainResourceAllocationList";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-TimeDomainResourceAllocationList";
    }

    @Override
    public Class<RRC_PUSCH_TimeDomainResourceAllocation> getItemType() {
        return RRC_PUSCH_TimeDomainResourceAllocation.class;
    }

}
