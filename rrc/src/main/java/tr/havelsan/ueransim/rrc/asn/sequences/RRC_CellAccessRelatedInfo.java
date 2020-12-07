/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityInfoList;

public class RRC_CellAccessRelatedInfo extends AsnSequence {
    public RRC_PLMN_IdentityInfoList plmn_IdentityList; // mandatory
    public RRC_cellReservedForOtherUse cellReservedForOtherUse; // optional

    public static class RRC_cellReservedForOtherUse extends AsnEnumerated {
        public static final RRC_cellReservedForOtherUse TRUE = new RRC_cellReservedForOtherUse(0);
    
        private RRC_cellReservedForOtherUse(long value) {
            super(value);
        }
    }
}

