/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_AS_Context extends RRC_Sequence {

    public RRC_ReestablishmentInfo reestablishmentInfo;
    public RRC_ConfigRestrictInfoSCG configRestrictInfo;
    public RRC_AS_Context__ext1 ext1;
    public RRC_AS_Context__ext2 ext2;
    public RRC_AS_Context__ext3 ext3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reestablishmentInfo","configRestrictInfo","ext1","ext2","ext3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reestablishmentInfo","configRestrictInfo","ext1","ext2","ext3" };
    }

    @Override
    public String getAsnName() {
        return "AS-Context";
    }

    @Override
    public String getXmlTagName() {
        return "AS-Context";
    }

}
