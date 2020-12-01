/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeAlignmentTimer;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TAG_Id;

public class RRC_TAG extends RRC_Sequence {

    public RRC_TAG_Id tag_Id;
    public RRC_TimeAlignmentTimer timeAlignmentTimer;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tag-Id","timeAlignmentTimer" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tag_Id","timeAlignmentTimer" };
    }

    @Override
    public String getAsnName() {
        return "TAG";
    }

    @Override
    public String getXmlTagName() {
        return "TAG";
    }

}
