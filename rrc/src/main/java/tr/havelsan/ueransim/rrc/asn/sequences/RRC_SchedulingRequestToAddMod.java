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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;

public class RRC_SchedulingRequestToAddMod extends AsnSequence {
    public RRC_SchedulingRequestId schedulingRequestId; // mandatory
    public RRC_sr_ProhibitTimer sr_ProhibitTimer; // optional
    public RRC_sr_TransMax sr_TransMax; // mandatory

    public static class RRC_sr_ProhibitTimer extends AsnEnumerated {
        public static final RRC_sr_ProhibitTimer MS1 = new RRC_sr_ProhibitTimer(0);
        public static final RRC_sr_ProhibitTimer MS2 = new RRC_sr_ProhibitTimer(1);
        public static final RRC_sr_ProhibitTimer MS4 = new RRC_sr_ProhibitTimer(2);
        public static final RRC_sr_ProhibitTimer MS8 = new RRC_sr_ProhibitTimer(3);
        public static final RRC_sr_ProhibitTimer MS16 = new RRC_sr_ProhibitTimer(4);
        public static final RRC_sr_ProhibitTimer MS32 = new RRC_sr_ProhibitTimer(5);
        public static final RRC_sr_ProhibitTimer MS64 = new RRC_sr_ProhibitTimer(6);
        public static final RRC_sr_ProhibitTimer MS128 = new RRC_sr_ProhibitTimer(7);
    
        private RRC_sr_ProhibitTimer(long value) {
            super(value);
        }
    }

    public static class RRC_sr_TransMax extends AsnEnumerated {
        public static final RRC_sr_TransMax N4 = new RRC_sr_TransMax(0);
        public static final RRC_sr_TransMax N8 = new RRC_sr_TransMax(1);
        public static final RRC_sr_TransMax N16 = new RRC_sr_TransMax(2);
        public static final RRC_sr_TransMax N32 = new RRC_sr_TransMax(3);
        public static final RRC_sr_TransMax N64 = new RRC_sr_TransMax(4);
        public static final RRC_sr_TransMax SPARE3 = new RRC_sr_TransMax(5);
        public static final RRC_sr_TransMax SPARE2 = new RRC_sr_TransMax(6);
        public static final RRC_sr_TransMax SPARE1 = new RRC_sr_TransMax(7);
    
        private RRC_sr_TransMax(long value) {
            super(value);
        }
    }
}

