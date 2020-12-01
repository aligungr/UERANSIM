/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCP_Config extends RRC_Sequence {

    public RRC_PDCP_Config__drb drb;
    public RRC_PDCP_Config__moreThanOneRLC moreThanOneRLC;
    public RRC_Integer t_Reordering;
    public RRC_PDCP_Config__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "drb","moreThanOneRLC","t-Reordering","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "drb","moreThanOneRLC","t_Reordering","ext1" };
    }

    @Override
    public String getAsnName() {
        return "PDCP-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PDCP-Config";
    }

}
