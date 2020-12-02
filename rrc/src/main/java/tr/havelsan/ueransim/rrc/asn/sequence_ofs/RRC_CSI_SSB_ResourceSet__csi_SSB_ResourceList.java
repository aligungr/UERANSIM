/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_CSI_SSB_ResourceSet__csi_SSB_ResourceList extends RRC_SequenceOf<RRC_SSB_Index> {

    @Override
    public Class<RRC_SSB_Index> getItemType() {
        return RRC_SSB_Index.class;
    }

}
