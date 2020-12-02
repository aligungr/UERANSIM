/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex4;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PortIndexFor8Ranks__portIndex4__rank2_4;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PortIndexFor8Ranks__portIndex4__rank3_4;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PortIndexFor8Ranks__portIndex4__rank4_4;

public class RRC_PortIndexFor8Ranks__portIndex4 extends RRC_Sequence {

    public RRC_PortIndex4 rank1_4;
    public RRC_PortIndexFor8Ranks__portIndex4__rank2_4 rank2_4;
    public RRC_PortIndexFor8Ranks__portIndex4__rank3_4 rank3_4;
    public RRC_PortIndexFor8Ranks__portIndex4__rank4_4 rank4_4;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rank1-4","rank2-4","rank3-4","rank4-4" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rank1_4","rank2_4","rank3_4","rank4_4" };
    }

}
