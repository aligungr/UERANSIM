/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PLMN_Identity;

public class RRC_PLMN_IdentityInfo__plmn_IdentityList extends RRC_SequenceOf<RRC_PLMN_Identity> {

    @Override
    public Class<RRC_PLMN_Identity> getItemType() {
        return RRC_PLMN_Identity.class;
    }

}
