/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_MeasConfig extends AsnSequence {
    public RRC_MeasObjectToRemoveList measObjectToRemoveList; // optional
    public RRC_MeasObjectToAddModList measObjectToAddModList; // optional
    public RRC_ReportConfigToRemoveList reportConfigToRemoveList; // optional
    public RRC_ReportConfigToAddModList reportConfigToAddModList; // optional
    public RRC_MeasIdToRemoveList measIdToRemoveList; // optional
    public RRC_MeasIdToAddModList measIdToAddModList; // optional
    public RRC_s_MeasureConfig_2 s_MeasureConfig; // optional
    public RRC_QuantityConfig quantityConfig; // optional
    public RRC_MeasGapConfig measGapConfig; // optional
    public RRC_MeasGapSharingConfig measGapSharingConfig; // optional

    public static class RRC_s_MeasureConfig_2 extends AsnChoice {
        public RRC_RSRP_Range ssb_RSRP;
        public RRC_RSRP_Range csi_RSRP;
    }
}

