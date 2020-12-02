/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ZP_CSI_RS_ResourceSet;

public class RRC_PDSCH_Config__sp_ZP_CSI_RS_ResourceSetsToAddModList extends RRC_SequenceOf<RRC_ZP_CSI_RS_ResourceSet> {

    @Override
    public Class<RRC_ZP_CSI_RS_ResourceSet> getItemType() {
        return RRC_ZP_CSI_RS_ResourceSet.class;
    }

}
