/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RAN_AreaCode;

public class RRC_RAN_AreaConfig__ran_AreaCodeList extends RRC_SequenceOf<RRC_RAN_AreaCode> {

    @Override
    public Class<RRC_RAN_AreaCode> getItemType() {
        return RRC_RAN_AreaCode.class;
    }

}
