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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_RedirectedCarrierInfo_EUTRA extends AsnSequence {
    public RRC_ARFCN_ValueEUTRA eutraFrequency; // mandatory
    public RRC_cnType cnType; // optional

    public static class RRC_cnType extends AsnEnumerated {
        public static final RRC_cnType EPC = new RRC_cnType(0);
        public static final RRC_cnType FIVEGC = new RRC_cnType(1);
    
        private RRC_cnType(long value) {
            super(value);
        }
    }
}

