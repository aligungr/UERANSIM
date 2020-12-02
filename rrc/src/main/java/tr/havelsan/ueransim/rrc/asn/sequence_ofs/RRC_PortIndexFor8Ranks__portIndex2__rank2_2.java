/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex2;

public class RRC_PortIndexFor8Ranks__portIndex2__rank2_2 extends RRC_SequenceOf<RRC_PortIndex2> {

    @Override
    public Class<RRC_PortIndex2> getItemType() {
        return RRC_PortIndex2.class;
    }

}
