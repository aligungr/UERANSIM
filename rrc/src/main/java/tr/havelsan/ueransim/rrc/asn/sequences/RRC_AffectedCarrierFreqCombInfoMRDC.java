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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_AffectedCarrierFreqCombEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_AffectedCarrierFreqCombNR;

public class RRC_AffectedCarrierFreqCombInfoMRDC extends AsnSequence {
    public RRC_VictimSystemType victimSystemType; // mandatory
    public RRC_interferenceDirectionMRDC interferenceDirectionMRDC; // mandatory
    public RRC_affectedCarrierFreqCombMRDC affectedCarrierFreqCombMRDC; // optional

    public static class RRC_affectedCarrierFreqCombMRDC extends AsnSequence {
        public RRC_AffectedCarrierFreqCombEUTRA affectedCarrierFreqCombEUTRA; // optional
        public RRC_AffectedCarrierFreqCombNR affectedCarrierFreqCombNR; // mandatory
    }

    public static class RRC_interferenceDirectionMRDC extends AsnEnumerated {
        public static final RRC_interferenceDirectionMRDC EUTRA_NR = new RRC_interferenceDirectionMRDC(0);
        public static final RRC_interferenceDirectionMRDC NR = new RRC_interferenceDirectionMRDC(1);
        public static final RRC_interferenceDirectionMRDC OTHER = new RRC_interferenceDirectionMRDC(2);
        public static final RRC_interferenceDirectionMRDC UTRA_NR_OTHER = new RRC_interferenceDirectionMRDC(3);
        public static final RRC_interferenceDirectionMRDC NR_OTHER = new RRC_interferenceDirectionMRDC(4);
        public static final RRC_interferenceDirectionMRDC SPARE3 = new RRC_interferenceDirectionMRDC(5);
        public static final RRC_interferenceDirectionMRDC SPARE2 = new RRC_interferenceDirectionMRDC(6);
        public static final RRC_interferenceDirectionMRDC SPARE1 = new RRC_interferenceDirectionMRDC(7);
    
        private RRC_interferenceDirectionMRDC(long value) {
            super(value);
        }
    }
}

