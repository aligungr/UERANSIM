/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_VictimSystemType extends AsnSequence {
    public RRC_gps gps; // optional
    public RRC_glonass glonass; // optional
    public RRC_bds bds; // optional
    public RRC_galileo galileo; // optional
    public RRC_wlan wlan; // optional
    public RRC_bluetooth bluetooth; // optional

    public static class RRC_gps extends AsnEnumerated {
        public static final RRC_gps TRUE = new RRC_gps(0);
    
        private RRC_gps(long value) {
            super(value);
        }
    }

    public static class RRC_bluetooth extends AsnEnumerated {
        public static final RRC_bluetooth TRUE = new RRC_bluetooth(0);
    
        private RRC_bluetooth(long value) {
            super(value);
        }
    }

    public static class RRC_glonass extends AsnEnumerated {
        public static final RRC_glonass TRUE = new RRC_glonass(0);
    
        private RRC_glonass(long value) {
            super(value);
        }
    }

    public static class RRC_bds extends AsnEnumerated {
        public static final RRC_bds TRUE = new RRC_bds(0);
    
        private RRC_bds(long value) {
            super(value);
        }
    }

    public static class RRC_wlan extends AsnEnumerated {
        public static final RRC_wlan TRUE = new RRC_wlan(0);
    
        private RRC_wlan(long value) {
            super(value);
        }
    }

    public static class RRC_galileo extends AsnEnumerated {
        public static final RRC_galileo TRUE = new RRC_galileo(0);
    
        private RRC_galileo(long value) {
            super(value);
        }
    }
}

