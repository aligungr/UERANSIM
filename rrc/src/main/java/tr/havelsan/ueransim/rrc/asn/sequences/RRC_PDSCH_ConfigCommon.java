/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDSCH_TimeDomainResourceAllocationList;

public class RRC_PDSCH_ConfigCommon extends RRC_Sequence {

    public RRC_PDSCH_TimeDomainResourceAllocationList pdsch_TimeDomainAllocationList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdsch-TimeDomainAllocationList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdsch_TimeDomainAllocationList" };
    }

    @Override
    public String getAsnName() {
        return "PDSCH-ConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "PDSCH-ConfigCommon";
    }

}
