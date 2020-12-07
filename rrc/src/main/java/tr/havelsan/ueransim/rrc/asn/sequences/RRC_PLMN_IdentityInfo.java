/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_CellIdentity;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_TrackingAreaCode;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RAN_AreaCode;

public class RRC_PLMN_IdentityInfo extends AsnSequence {
    public RRC_plmn_IdentityList plmn_IdentityList; // mandatory, SIZE(1..12)
    public RRC_TrackingAreaCode trackingAreaCode; // optional
    public RRC_RAN_AreaCode ranac; // optional
    public RRC_CellIdentity cellIdentity; // mandatory
    public RRC_cellReservedForOperatorUse cellReservedForOperatorUse; // mandatory

    public static class RRC_cellReservedForOperatorUse extends AsnEnumerated {
        public static final RRC_cellReservedForOperatorUse RESERVED = new RRC_cellReservedForOperatorUse(0);
        public static final RRC_cellReservedForOperatorUse NOTRESERVED = new RRC_cellReservedForOperatorUse(1);
    
        private RRC_cellReservedForOperatorUse(long value) {
            super(value);
        }
    }

    // SIZE(1..12)
    public static class RRC_plmn_IdentityList extends AsnSequenceOf<RRC_PLMN_Identity> {
    }
}

