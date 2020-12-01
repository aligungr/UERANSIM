/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_GapConfig extends RRC_Sequence {

    public RRC_Integer gapOffset;
    public RRC_Integer mgl;
    public RRC_Integer mgrp;
    public RRC_Integer mgta;
    public RRC_GapConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "gapOffset","mgl","mgrp","mgta","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "gapOffset","mgl","mgrp","mgta","ext1" };
    }

    @Override
    public String getAsnName() {
        return "GapConfig";
    }

    @Override
    public String getXmlTagName() {
        return "GapConfig";
    }

}
