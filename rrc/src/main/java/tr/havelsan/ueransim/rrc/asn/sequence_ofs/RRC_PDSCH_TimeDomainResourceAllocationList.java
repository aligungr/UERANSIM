/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PDSCH_TimeDomainResourceAllocation;

public class RRC_PDSCH_TimeDomainResourceAllocationList extends RRC_SequenceOf<RRC_PDSCH_TimeDomainResourceAllocation> {

    @Override
    public String getAsnName() {
        return "PDSCH-TimeDomainResourceAllocationList";
    }

    @Override
    public String getXmlTagName() {
        return "PDSCH-TimeDomainResourceAllocationList";
    }

    @Override
    public Class<RRC_PDSCH_TimeDomainResourceAllocation> getItemType() {
        return RRC_PDSCH_TimeDomainResourceAllocation.class;
    }

}
