/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SCellIndex;

public class RRC_SCellConfig extends RRC_Sequence {

    public RRC_SCellIndex sCellIndex;
    public RRC_ServingCellConfigCommon sCellConfigCommon;
    public RRC_ServingCellConfig sCellConfigDedicated;
    public RRC_SCellConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sCellIndex","sCellConfigCommon","sCellConfigDedicated","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sCellIndex","sCellConfigCommon","sCellConfigDedicated","ext1" };
    }

    @Override
    public String getAsnName() {
        return "SCellConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SCellConfig";
    }

}
