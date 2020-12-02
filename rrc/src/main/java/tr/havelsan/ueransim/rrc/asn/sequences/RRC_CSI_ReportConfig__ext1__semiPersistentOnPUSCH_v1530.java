/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_ReportConfig__ext1__semiPersistentOnPUSCH_v1530 extends RRC_Sequence {

    public RRC_Integer reportSlotConfig_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportSlotConfig-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportSlotConfig_v1530" };
    }

}
