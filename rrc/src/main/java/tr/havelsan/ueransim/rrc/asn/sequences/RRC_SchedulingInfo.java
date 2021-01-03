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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SIB_Mapping;

public class RRC_SchedulingInfo extends AsnSequence {
    public RRC_si_BroadcastStatus si_BroadcastStatus; // mandatory
    public RRC_si_Periodicity si_Periodicity; // mandatory
    public RRC_SIB_Mapping sib_MappingInfo; // mandatory

    public static class RRC_si_Periodicity extends AsnEnumerated {
        public static final RRC_si_Periodicity RF8 = new RRC_si_Periodicity(0);
        public static final RRC_si_Periodicity RF16 = new RRC_si_Periodicity(1);
        public static final RRC_si_Periodicity RF32 = new RRC_si_Periodicity(2);
        public static final RRC_si_Periodicity RF64 = new RRC_si_Periodicity(3);
        public static final RRC_si_Periodicity RF128 = new RRC_si_Periodicity(4);
        public static final RRC_si_Periodicity RF256 = new RRC_si_Periodicity(5);
        public static final RRC_si_Periodicity RF512 = new RRC_si_Periodicity(6);
    
        private RRC_si_Periodicity(long value) {
            super(value);
        }
    }

    public static class RRC_si_BroadcastStatus extends AsnEnumerated {
        public static final RRC_si_BroadcastStatus BROADCASTING = new RRC_si_BroadcastStatus(0);
        public static final RRC_si_BroadcastStatus NOTBROADCASTING = new RRC_si_BroadcastStatus(1);
    
        private RRC_si_BroadcastStatus(long value) {
            super(value);
        }
    }
}

