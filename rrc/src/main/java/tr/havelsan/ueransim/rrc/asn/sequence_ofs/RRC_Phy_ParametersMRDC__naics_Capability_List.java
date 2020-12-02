/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_NAICS_Capability_Entry;

public class RRC_Phy_ParametersMRDC__naics_Capability_List extends RRC_SequenceOf<RRC_NAICS_Capability_Entry> {

    @Override
    public Class<RRC_NAICS_Capability_Entry> getItemType() {
        return RRC_NAICS_Capability_Entry.class;
    }

}
