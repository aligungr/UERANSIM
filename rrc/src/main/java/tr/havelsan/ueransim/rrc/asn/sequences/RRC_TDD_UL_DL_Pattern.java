/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_TDD_UL_DL_Pattern extends AsnSequence {
    public RRC_dl_UL_TransmissionPeriodicity dl_UL_TransmissionPeriodicity; // mandatory
    public AsnInteger nrofDownlinkSlots; // mandatory, VALUE(0..320)
    public AsnInteger nrofDownlinkSymbols; // mandatory, VALUE(0..13)
    public AsnInteger nrofUplinkSlots; // mandatory, VALUE(0..320)
    public AsnInteger nrofUplinkSymbols; // mandatory, VALUE(0..13)
    public RRC_ext1_52 ext1; // optional

    public static class RRC_dl_UL_TransmissionPeriodicity extends AsnEnumerated {
        public static final RRC_dl_UL_TransmissionPeriodicity MS0P5 = new RRC_dl_UL_TransmissionPeriodicity(0);
        public static final RRC_dl_UL_TransmissionPeriodicity MS0P625 = new RRC_dl_UL_TransmissionPeriodicity(1);
        public static final RRC_dl_UL_TransmissionPeriodicity MS1 = new RRC_dl_UL_TransmissionPeriodicity(2);
        public static final RRC_dl_UL_TransmissionPeriodicity MS1P25 = new RRC_dl_UL_TransmissionPeriodicity(3);
        public static final RRC_dl_UL_TransmissionPeriodicity MS2 = new RRC_dl_UL_TransmissionPeriodicity(4);
        public static final RRC_dl_UL_TransmissionPeriodicity MS2P5 = new RRC_dl_UL_TransmissionPeriodicity(5);
        public static final RRC_dl_UL_TransmissionPeriodicity MS5 = new RRC_dl_UL_TransmissionPeriodicity(6);
        public static final RRC_dl_UL_TransmissionPeriodicity MS10 = new RRC_dl_UL_TransmissionPeriodicity(7);
    
        private RRC_dl_UL_TransmissionPeriodicity(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_52 extends AsnSequence {
        public RRC_dl_UL_TransmissionPeriodicity_v1530 dl_UL_TransmissionPeriodicity_v1530; // optional
    
        public static class RRC_dl_UL_TransmissionPeriodicity_v1530 extends AsnEnumerated {
            public static final RRC_dl_UL_TransmissionPeriodicity_v1530 MS3 = new RRC_dl_UL_TransmissionPeriodicity_v1530(0);
            public static final RRC_dl_UL_TransmissionPeriodicity_v1530 MS4 = new RRC_dl_UL_TransmissionPeriodicity_v1530(1);
        
            private RRC_dl_UL_TransmissionPeriodicity_v1530(long value) {
                super(value);
            }
        }
    }
}

