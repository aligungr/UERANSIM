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

public class RRC_SIB_TypeInfo extends AsnSequence {
    public RRC_type type; // mandatory
    public AsnInteger valueTag; // optional, VALUE(0..31)
    public RRC_areaScope areaScope; // optional

    public static class RRC_areaScope extends AsnEnumerated {
        public static final RRC_areaScope TRUE = new RRC_areaScope(0);
    
        private RRC_areaScope(long value) {
            super(value);
        }
    }

    public static class RRC_type extends AsnEnumerated {
        public static final RRC_type SIBTYPE2 = new RRC_type(0);
        public static final RRC_type SIBTYPE3 = new RRC_type(1);
        public static final RRC_type SIBTYPE4 = new RRC_type(2);
        public static final RRC_type SIBTYPE5 = new RRC_type(3);
        public static final RRC_type SIBTYPE6 = new RRC_type(4);
        public static final RRC_type SIBTYPE7 = new RRC_type(5);
        public static final RRC_type SIBTYPE8 = new RRC_type(6);
        public static final RRC_type SIBTYPE9 = new RRC_type(7);
        public static final RRC_type SPARE8 = new RRC_type(8);
        public static final RRC_type SPARE7 = new RRC_type(9);
        public static final RRC_type SPARE6 = new RRC_type(10);
        public static final RRC_type SPARE5 = new RRC_type(11);
        public static final RRC_type SPARE4 = new RRC_type(12);
        public static final RRC_type SPARE3 = new RRC_type(13);
        public static final RRC_type SPARE2 = new RRC_type(14);
        public static final RRC_type SPARE1 = new RRC_type(15);
    
        private RRC_type(long value) {
            super(value);
        }
    }
}

