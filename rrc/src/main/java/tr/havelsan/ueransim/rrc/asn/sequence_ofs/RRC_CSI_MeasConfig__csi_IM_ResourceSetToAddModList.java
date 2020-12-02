/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_IM_ResourceSet;

public class RRC_CSI_MeasConfig__csi_IM_ResourceSetToAddModList extends RRC_SequenceOf<RRC_CSI_IM_ResourceSet> {

    @Override
    public Class<RRC_CSI_IM_ResourceSet> getItemType() {
        return RRC_CSI_IM_ResourceSet.class;
    }

}
