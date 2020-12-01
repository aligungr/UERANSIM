/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRB_Identity;

public class RRC_SRB_ToAddMod extends RRC_Sequence {

    public RRC_SRB_Identity srb_Identity;
    public RRC_Integer reestablishPDCP;
    public RRC_Integer discardOnPDCP;
    public RRC_PDCP_Config pdcp_Config;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srb-Identity","reestablishPDCP","discardOnPDCP","pdcp-Config" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srb_Identity","reestablishPDCP","discardOnPDCP","pdcp_Config" };
    }

    @Override
    public String getAsnName() {
        return "SRB-ToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "SRB-ToAddMod";
    }

}
