/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TAG_Id;

public class RRC_TAG_Config__tag_ToReleaseList extends RRC_SequenceOf<RRC_TAG_Id> {

    @Override
    public Class<RRC_TAG_Id> getItemType() {
        return RRC_TAG_Id.class;
    }

}
