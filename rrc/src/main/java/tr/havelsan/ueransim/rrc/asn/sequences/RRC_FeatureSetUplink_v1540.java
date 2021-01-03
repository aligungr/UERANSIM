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

public class RRC_FeatureSetUplink_v1540 extends AsnSequence {
    public RRC_zeroSlotOffsetAperiodicSRS zeroSlotOffsetAperiodicSRS; // optional
    public RRC_pa_PhaseDiscontinuityImpacts pa_PhaseDiscontinuityImpacts; // optional
    public RRC_pusch_SeparationWithGap pusch_SeparationWithGap; // optional
    public RRC_pusch_ProcessingType2 pusch_ProcessingType2; // optional
    public RRC_ul_MCS_TableAlt_DynamicIndication ul_MCS_TableAlt_DynamicIndication; // optional

    public static class RRC_ul_MCS_TableAlt_DynamicIndication extends AsnEnumerated {
        public static final RRC_ul_MCS_TableAlt_DynamicIndication SUPPORTED = new RRC_ul_MCS_TableAlt_DynamicIndication(0);
    
        private RRC_ul_MCS_TableAlt_DynamicIndication(long value) {
            super(value);
        }
    }

    public static class RRC_pusch_ProcessingType2 extends AsnSequence {
        public RRC_ProcessingParameters scs_15kHz; // optional
        public RRC_ProcessingParameters scs_30kHz; // optional
        public RRC_ProcessingParameters scs_60kHz; // optional
    }

    public static class RRC_pusch_SeparationWithGap extends AsnEnumerated {
        public static final RRC_pusch_SeparationWithGap SUPPORTED = new RRC_pusch_SeparationWithGap(0);
    
        private RRC_pusch_SeparationWithGap(long value) {
            super(value);
        }
    }

    public static class RRC_zeroSlotOffsetAperiodicSRS extends AsnEnumerated {
        public static final RRC_zeroSlotOffsetAperiodicSRS SUPPORTED = new RRC_zeroSlotOffsetAperiodicSRS(0);
    
        private RRC_zeroSlotOffsetAperiodicSRS(long value) {
            super(value);
        }
    }

    public static class RRC_pa_PhaseDiscontinuityImpacts extends AsnEnumerated {
        public static final RRC_pa_PhaseDiscontinuityImpacts SUPPORTED = new RRC_pa_PhaseDiscontinuityImpacts(0);
    
        private RRC_pa_PhaseDiscontinuityImpacts(long value) {
            super(value);
        }
    }
}

