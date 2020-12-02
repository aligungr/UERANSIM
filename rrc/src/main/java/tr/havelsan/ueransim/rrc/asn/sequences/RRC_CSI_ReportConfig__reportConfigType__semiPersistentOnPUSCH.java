/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUSCH__reportSlotOffsetList;

public class RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUSCH extends RRC_Sequence {

    public RRC_Integer reportSlotConfig;
    public RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUSCH__reportSlotOffsetList reportSlotOffsetList;
    public RRC_P0_PUSCH_AlphaSetId p0alpha;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportSlotConfig","reportSlotOffsetList","p0alpha" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportSlotConfig","reportSlotOffsetList","p0alpha" };
    }

}
