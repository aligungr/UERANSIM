/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;

public class RRC_SPS_Config extends RRC_Sequence {

    public RRC_Integer periodicity;
    public RRC_Integer nrofHARQ_Processes;
    public RRC_PUCCH_ResourceId n1PUCCH_AN;
    public RRC_Integer mcs_Table;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodicity","nrofHARQ-Processes","n1PUCCH-AN","mcs-Table" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodicity","nrofHARQ_Processes","n1PUCCH_AN","mcs_Table" };
    }

    @Override
    public String getAsnName() {
        return "SPS-Config";
    }

    @Override
    public String getXmlTagName() {
        return "SPS-Config";
    }

}
