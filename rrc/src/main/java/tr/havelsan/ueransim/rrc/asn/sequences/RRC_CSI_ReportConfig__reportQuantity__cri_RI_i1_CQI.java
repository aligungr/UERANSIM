/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_ReportConfig__reportQuantity__cri_RI_i1_CQI extends RRC_Sequence {

    public RRC_Integer pdsch_BundleSizeForCSI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdsch-BundleSizeForCSI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdsch_BundleSizeForCSI" };
    }

}
