/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex8;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_PortIndexFor8Ranks__portIndex8 extends RRC_Sequence {

    public RRC_PortIndex8 rank1_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank2_8 rank2_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank3_8 rank3_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank4_8 rank4_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank5_8 rank5_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank6_8 rank6_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank7_8 rank7_8;
    public RRC_PortIndexFor8Ranks__portIndex8__rank8_8 rank8_8;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rank1-8","rank2-8","rank3-8","rank4-8","rank5-8","rank6-8","rank7-8","rank8-8" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rank1_8","rank2_8","rank3_8","rank4_8","rank5_8","rank6_8","rank7_8","rank8_8" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
