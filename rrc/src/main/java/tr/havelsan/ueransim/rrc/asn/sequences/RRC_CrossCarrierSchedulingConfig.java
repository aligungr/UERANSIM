/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_CrossCarrierSchedulingConfig extends AsnSequence {
    public RRC_schedulingCellInfo schedulingCellInfo; // mandatory

    public static class RRC_schedulingCellInfo extends AsnChoice {
        public RRC_own own;
        public RRC_other other;
    
        public static class RRC_other extends AsnSequence {
            public RRC_ServCellIndex schedulingCellId; // mandatory
            public AsnInteger cif_InSchedulingCell; // mandatory, VALUE(1..7)
        }
    
        public static class RRC_own extends AsnSequence {
            public AsnBoolean cif_Presence; // mandatory
        }
    }
}

