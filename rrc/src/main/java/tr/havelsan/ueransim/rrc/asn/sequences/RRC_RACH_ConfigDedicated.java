/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RACH_ConfigDedicated extends RRC_Sequence {

    public RRC_CFRA cfra;
    public RRC_RA_Prioritization ra_Prioritization;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cfra","ra-Prioritization" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cfra","ra_Prioritization" };
    }

    @Override
    public String getAsnName() {
        return "RACH-ConfigDedicated";
    }

    @Override
    public String getXmlTagName() {
        return "RACH-ConfigDedicated";
    }

}
