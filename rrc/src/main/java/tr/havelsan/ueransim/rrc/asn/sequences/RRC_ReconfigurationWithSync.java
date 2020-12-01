/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_ReconfigurationWithSync__rach_ConfigDedicated;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_ReconfigurationWithSync extends RRC_Sequence {

    public RRC_ServingCellConfigCommon spCellConfigCommon;
    public RRC_RNTI_Value newUE_Identity;
    public RRC_Integer t304;
    public RRC_ReconfigurationWithSync__rach_ConfigDedicated rach_ConfigDedicated;
    public RRC_ReconfigurationWithSync__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "spCellConfigCommon","newUE-Identity","t304","rach-ConfigDedicated","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "spCellConfigCommon","newUE_Identity","t304","rach_ConfigDedicated","ext1" };
    }

    @Override
    public String getAsnName() {
        return "ReconfigurationWithSync";
    }

    @Override
    public String getXmlTagName() {
        return "ReconfigurationWithSync";
    }

}
