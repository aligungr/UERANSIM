/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_DRB_ToAddMod__cnAssociation;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;

public class RRC_DRB_ToAddMod extends RRC_Sequence {

    public RRC_DRB_ToAddMod__cnAssociation cnAssociation;
    public RRC_DRB_Identity drb_Identity;
    public RRC_Integer reestablishPDCP;
    public RRC_Integer recoverPDCP;
    public RRC_PDCP_Config pdcp_Config;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cnAssociation","drb-Identity","reestablishPDCP","recoverPDCP","pdcp-Config" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cnAssociation","drb_Identity","reestablishPDCP","recoverPDCP","pdcp_Config" };
    }

    @Override
    public String getAsnName() {
        return "DRB-ToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-ToAddMod";
    }

}
