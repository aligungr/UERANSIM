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

public class RRC_RLC_Parameters extends AsnSequence {
    public RRC_am_WithShortSN am_WithShortSN; // optional
    public RRC_um_WithShortSN um_WithShortSN; // optional
    public RRC_um_WithLongSN um_WithLongSN; // optional

    public static class RRC_um_WithLongSN extends AsnEnumerated {
        public static final RRC_um_WithLongSN SUPPORTED = new RRC_um_WithLongSN(0);
    
        private RRC_um_WithLongSN(long value) {
            super(value);
        }
    }

    public static class RRC_um_WithShortSN extends AsnEnumerated {
        public static final RRC_um_WithShortSN SUPPORTED = new RRC_um_WithShortSN(0);
    
        private RRC_um_WithShortSN(long value) {
            super(value);
        }
    }

    public static class RRC_am_WithShortSN extends AsnEnumerated {
        public static final RRC_am_WithShortSN SUPPORTED = new RRC_am_WithShortSN(0);
    
        private RRC_am_WithShortSN(long value) {
            super(value);
        }
    }
}

