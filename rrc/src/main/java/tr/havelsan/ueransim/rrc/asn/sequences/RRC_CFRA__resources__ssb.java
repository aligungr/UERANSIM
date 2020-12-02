/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CFRA__resources__ssb__ssb_ResourceList;

public class RRC_CFRA__resources__ssb extends RRC_Sequence {

    public RRC_CFRA__resources__ssb__ssb_ResourceList ssb_ResourceList;
    public RRC_Integer ra_ssb_OccasionMaskIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-ResourceList","ra-ssb-OccasionMaskIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_ResourceList","ra_ssb_OccasionMaskIndex" };
    }

}
