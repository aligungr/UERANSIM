/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_CC_SetIndex extends RRC_Sequence {

    public RRC_Integer cc_SetIndex;
    public RRC_Integer cc_IndexInOneCC_Set;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cc-SetIndex","cc-IndexInOneCC-Set" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cc_SetIndex","cc_IndexInOneCC_Set" };
    }

    @Override
    public String getAsnName() {
        return "SRS-CC-SetIndex";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-CC-SetIndex";
    }

}
