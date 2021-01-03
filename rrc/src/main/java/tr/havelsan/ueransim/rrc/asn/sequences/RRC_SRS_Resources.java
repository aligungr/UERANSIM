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

public class RRC_SRS_Resources extends AsnSequence {
    public RRC_maxNumberAperiodicSRS_PerBWP maxNumberAperiodicSRS_PerBWP; // mandatory
    public AsnInteger maxNumberAperiodicSRS_PerBWP_PerSlot; // mandatory, VALUE(1..6)
    public RRC_maxNumberPeriodicSRS_PerBWP maxNumberPeriodicSRS_PerBWP; // mandatory
    public AsnInteger maxNumberPeriodicSRS_PerBWP_PerSlot; // mandatory, VALUE(1..6)
    public RRC_maxNumberSemiPersistentSRS_PerBWP maxNumberSemiPersistentSRS_PerBWP; // mandatory
    public AsnInteger maxNumberSemiPersistentSRS_PerBWP_PerSlot; // mandatory, VALUE(1..6)
    public RRC_maxNumberSRS_Ports_PerResource maxNumberSRS_Ports_PerResource; // mandatory

    public static class RRC_maxNumberSRS_Ports_PerResource extends AsnEnumerated {
        public static final RRC_maxNumberSRS_Ports_PerResource N1 = new RRC_maxNumberSRS_Ports_PerResource(0);
        public static final RRC_maxNumberSRS_Ports_PerResource N2 = new RRC_maxNumberSRS_Ports_PerResource(1);
        public static final RRC_maxNumberSRS_Ports_PerResource N4 = new RRC_maxNumberSRS_Ports_PerResource(2);
    
        private RRC_maxNumberSRS_Ports_PerResource(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberSemiPersistentSRS_PerBWP extends AsnEnumerated {
        public static final RRC_maxNumberSemiPersistentSRS_PerBWP N1 = new RRC_maxNumberSemiPersistentSRS_PerBWP(0);
        public static final RRC_maxNumberSemiPersistentSRS_PerBWP N2 = new RRC_maxNumberSemiPersistentSRS_PerBWP(1);
        public static final RRC_maxNumberSemiPersistentSRS_PerBWP N4 = new RRC_maxNumberSemiPersistentSRS_PerBWP(2);
        public static final RRC_maxNumberSemiPersistentSRS_PerBWP N8 = new RRC_maxNumberSemiPersistentSRS_PerBWP(3);
        public static final RRC_maxNumberSemiPersistentSRS_PerBWP N16 = new RRC_maxNumberSemiPersistentSRS_PerBWP(4);
    
        private RRC_maxNumberSemiPersistentSRS_PerBWP(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberAperiodicSRS_PerBWP extends AsnEnumerated {
        public static final RRC_maxNumberAperiodicSRS_PerBWP N1 = new RRC_maxNumberAperiodicSRS_PerBWP(0);
        public static final RRC_maxNumberAperiodicSRS_PerBWP N2 = new RRC_maxNumberAperiodicSRS_PerBWP(1);
        public static final RRC_maxNumberAperiodicSRS_PerBWP N4 = new RRC_maxNumberAperiodicSRS_PerBWP(2);
        public static final RRC_maxNumberAperiodicSRS_PerBWP N8 = new RRC_maxNumberAperiodicSRS_PerBWP(3);
        public static final RRC_maxNumberAperiodicSRS_PerBWP N16 = new RRC_maxNumberAperiodicSRS_PerBWP(4);
    
        private RRC_maxNumberAperiodicSRS_PerBWP(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberPeriodicSRS_PerBWP extends AsnEnumerated {
        public static final RRC_maxNumberPeriodicSRS_PerBWP N1 = new RRC_maxNumberPeriodicSRS_PerBWP(0);
        public static final RRC_maxNumberPeriodicSRS_PerBWP N2 = new RRC_maxNumberPeriodicSRS_PerBWP(1);
        public static final RRC_maxNumberPeriodicSRS_PerBWP N4 = new RRC_maxNumberPeriodicSRS_PerBWP(2);
        public static final RRC_maxNumberPeriodicSRS_PerBWP N8 = new RRC_maxNumberPeriodicSRS_PerBWP(3);
        public static final RRC_maxNumberPeriodicSRS_PerBWP N16 = new RRC_maxNumberPeriodicSRS_PerBWP(4);
    
        private RRC_maxNumberPeriodicSRS_PerBWP(long value) {
            super(value);
        }
    }
}

