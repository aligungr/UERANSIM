/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_BCCH_DL_SCH_MessageType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BCCH_DL_SCH_Message extends RRC_Sequence {

    public RRC_BCCH_DL_SCH_MessageType message;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "message" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "message" };
    }

    @Override
    public String getAsnName() {
        return "BCCH-DL-SCH-Message";
    }

    @Override
    public String getXmlTagName() {
        return "BCCH-DL-SCH-Message";
    }

}
