/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_NZP_CSI_RS_ResourceSet__nzp_CSI_RS_Resources;

public class RRC_NZP_CSI_RS_ResourceSet extends RRC_Sequence {

    public RRC_NZP_CSI_RS_ResourceSetId nzp_CSI_ResourceSetId;
    public RRC_NZP_CSI_RS_ResourceSet__nzp_CSI_RS_Resources nzp_CSI_RS_Resources;
    public RRC_Integer repetition;
    public RRC_Integer aperiodicTriggeringOffset;
    public RRC_Integer trs_Info;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nzp-CSI-ResourceSetId","nzp-CSI-RS-Resources","repetition","aperiodicTriggeringOffset","trs-Info" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nzp_CSI_ResourceSetId","nzp_CSI_RS_Resources","repetition","aperiodicTriggeringOffset","trs_Info" };
    }

    @Override
    public String getAsnName() {
        return "NZP-CSI-RS-ResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "NZP-CSI-RS-ResourceSet";
    }

}
