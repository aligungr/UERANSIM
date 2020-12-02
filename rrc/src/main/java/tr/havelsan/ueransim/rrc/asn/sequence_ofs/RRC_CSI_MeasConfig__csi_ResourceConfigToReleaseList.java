/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ResourceConfigId;

public class RRC_CSI_MeasConfig__csi_ResourceConfigToReleaseList extends RRC_SequenceOf<RRC_CSI_ResourceConfigId> {

    @Override
    public Class<RRC_CSI_ResourceConfigId> getItemType() {
        return RRC_CSI_ResourceConfigId.class;
    }

}
