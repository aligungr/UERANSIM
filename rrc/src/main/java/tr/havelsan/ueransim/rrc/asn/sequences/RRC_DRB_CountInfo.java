/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;

public class RRC_DRB_CountInfo extends AsnSequence {
    public RRC_DRB_Identity drb_Identity; // mandatory
    public RRC_count_Uplink count_Uplink; // mandatory, VALUE(0..4294967295)
    public RRC_count_Downlink count_Downlink; // mandatory, VALUE(0..4294967295)

    // VALUE(0..4294967295)
    public static class RRC_count_Downlink extends AsnInteger {
        public RRC_count_Downlink() {
        }
        
        public RRC_count_Downlink(long value) {
            super(value);
        }
    }

    // VALUE(0..4294967295)
    public static class RRC_count_Uplink extends AsnInteger {
        public RRC_count_Uplink() {
        }
        
        public RRC_count_Uplink(long value) {
            super(value);
        }
    }
}

