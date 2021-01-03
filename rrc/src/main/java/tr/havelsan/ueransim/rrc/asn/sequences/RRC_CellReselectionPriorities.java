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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqPriorityListEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqPriorityListNR;

public class RRC_CellReselectionPriorities extends AsnSequence {
    public RRC_FreqPriorityListEUTRA freqPriorityListEUTRA; // optional
    public RRC_FreqPriorityListNR freqPriorityListNR; // optional
    public RRC_t320 t320; // optional

    public static class RRC_t320 extends AsnEnumerated {
        public static final RRC_t320 MIN5 = new RRC_t320(0);
        public static final RRC_t320 MIN10 = new RRC_t320(1);
        public static final RRC_t320 MIN20 = new RRC_t320(2);
        public static final RRC_t320 MIN30 = new RRC_t320(3);
        public static final RRC_t320 MIN60 = new RRC_t320(4);
        public static final RRC_t320 MIN120 = new RRC_t320(5);
        public static final RRC_t320 MIN180 = new RRC_t320(6);
        public static final RRC_t320 SPARE1 = new RRC_t320(7);
    
        private RRC_t320(long value) {
            super(value);
        }
    }
}

