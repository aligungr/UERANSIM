/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex2;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PortIndexFor8Ranks__portIndex2__rank2_2;

public class RRC_PortIndexFor8Ranks__portIndex2 extends RRC_Sequence {

    public RRC_PortIndex2 rank1_2;
    public RRC_PortIndexFor8Ranks__portIndex2__rank2_2 rank2_2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rank1-2","rank2-2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rank1_2","rank2_2" };
    }

}
