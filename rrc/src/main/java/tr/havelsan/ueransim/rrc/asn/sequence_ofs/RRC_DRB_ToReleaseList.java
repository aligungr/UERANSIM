/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;

public class RRC_DRB_ToReleaseList extends RRC_SequenceOf<RRC_DRB_Identity> {

    @Override
    public String getAsnName() {
        return "DRB-ToReleaseList";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-ToReleaseList";
    }

    @Override
    public Class<RRC_DRB_Identity> getItemType() {
        return RRC_DRB_Identity.class;
    }

}
