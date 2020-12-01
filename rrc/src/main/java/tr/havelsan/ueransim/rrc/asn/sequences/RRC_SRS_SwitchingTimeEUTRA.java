/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_SwitchingTimeEUTRA extends RRC_Sequence {

    public RRC_Integer switchingTimeDL;
    public RRC_Integer switchingTimeUL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "switchingTimeDL","switchingTimeUL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "switchingTimeDL","switchingTimeUL" };
    }

    @Override
    public String getAsnName() {
        return "SRS-SwitchingTimeEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-SwitchingTimeEUTRA";
    }

}
