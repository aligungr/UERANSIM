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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasIdToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasObjectToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ReportConfigToAddModList;

public class RRC_VarMeasConfig extends AsnSequence {
    public RRC_MeasIdToAddModList measIdList; // optional
    public RRC_MeasObjectToAddModList measObjectList; // optional
    public RRC_ReportConfigToAddModList reportConfigList; // optional
    public RRC_QuantityConfig quantityConfig; // optional
    public RRC_s_MeasureConfig_1 s_MeasureConfig; // optional

    public static class RRC_s_MeasureConfig_1 extends AsnChoice {
        public RRC_RSRP_Range ssb_RSRP;
        public RRC_RSRP_Range csi_RSRP;
    }
}

