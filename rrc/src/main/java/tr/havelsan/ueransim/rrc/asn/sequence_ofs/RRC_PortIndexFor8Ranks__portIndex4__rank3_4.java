/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex4;

public class RRC_PortIndexFor8Ranks__portIndex4__rank3_4 extends RRC_SequenceOf<RRC_PortIndex4> {

    @Override
    public Class<RRC_PortIndex4> getItemType() {
        return RRC_PortIndex4.class;
    }

}
