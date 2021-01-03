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

public class RRC_ConnEstFailureControl extends AsnSequence {
    public RRC_connEstFailCount connEstFailCount; // mandatory
    public RRC_connEstFailOffsetValidity connEstFailOffsetValidity; // mandatory
    public AsnInteger connEstFailOffset; // optional, VALUE(0..15)

    public static class RRC_connEstFailCount extends AsnEnumerated {
        public static final RRC_connEstFailCount N1 = new RRC_connEstFailCount(0);
        public static final RRC_connEstFailCount N2 = new RRC_connEstFailCount(1);
        public static final RRC_connEstFailCount N3 = new RRC_connEstFailCount(2);
        public static final RRC_connEstFailCount N4 = new RRC_connEstFailCount(3);
    
        private RRC_connEstFailCount(long value) {
            super(value);
        }
    }

    public static class RRC_connEstFailOffsetValidity extends AsnEnumerated {
        public static final RRC_connEstFailOffsetValidity S30 = new RRC_connEstFailOffsetValidity(0);
        public static final RRC_connEstFailOffsetValidity S60 = new RRC_connEstFailOffsetValidity(1);
        public static final RRC_connEstFailOffsetValidity S120 = new RRC_connEstFailOffsetValidity(2);
        public static final RRC_connEstFailOffsetValidity S240 = new RRC_connEstFailOffsetValidity(3);
        public static final RRC_connEstFailOffsetValidity S300 = new RRC_connEstFailOffsetValidity(4);
        public static final RRC_connEstFailOffsetValidity S420 = new RRC_connEstFailOffsetValidity(5);
        public static final RRC_connEstFailOffsetValidity S600 = new RRC_connEstFailOffsetValidity(6);
        public static final RRC_connEstFailOffsetValidity S900 = new RRC_connEstFailOffsetValidity(7);
    
        private RRC_connEstFailOffsetValidity(long value) {
            super(value);
        }
    }
}

