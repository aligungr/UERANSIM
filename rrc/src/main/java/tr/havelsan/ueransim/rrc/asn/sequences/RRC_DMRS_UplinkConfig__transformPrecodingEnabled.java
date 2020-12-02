/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DMRS_UplinkConfig__transformPrecodingEnabled extends RRC_Sequence {

    public RRC_Integer nPUSCH_Identity;
    public RRC_Integer sequenceGroupHopping;
    public RRC_Integer sequenceHopping;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nPUSCH-Identity","sequenceGroupHopping","sequenceHopping" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nPUSCH_Identity","sequenceGroupHopping","sequenceHopping" };
    }

}
