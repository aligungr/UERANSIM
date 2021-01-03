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

public class RRC_SpatialRelations extends AsnSequence {
    public RRC_maxNumberConfiguredSpatialRelations maxNumberConfiguredSpatialRelations; // mandatory
    public RRC_maxNumberActiveSpatialRelations maxNumberActiveSpatialRelations; // mandatory
    public RRC_additionalActiveSpatialRelationPUCCH additionalActiveSpatialRelationPUCCH; // optional
    public RRC_maxNumberDL_RS_QCL_TypeD maxNumberDL_RS_QCL_TypeD; // mandatory

    public static class RRC_maxNumberDL_RS_QCL_TypeD extends AsnEnumerated {
        public static final RRC_maxNumberDL_RS_QCL_TypeD N1 = new RRC_maxNumberDL_RS_QCL_TypeD(0);
        public static final RRC_maxNumberDL_RS_QCL_TypeD N2 = new RRC_maxNumberDL_RS_QCL_TypeD(1);
        public static final RRC_maxNumberDL_RS_QCL_TypeD N4 = new RRC_maxNumberDL_RS_QCL_TypeD(2);
        public static final RRC_maxNumberDL_RS_QCL_TypeD N8 = new RRC_maxNumberDL_RS_QCL_TypeD(3);
        public static final RRC_maxNumberDL_RS_QCL_TypeD N14 = new RRC_maxNumberDL_RS_QCL_TypeD(4);
    
        private RRC_maxNumberDL_RS_QCL_TypeD(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberConfiguredSpatialRelations extends AsnEnumerated {
        public static final RRC_maxNumberConfiguredSpatialRelations N4 = new RRC_maxNumberConfiguredSpatialRelations(0);
        public static final RRC_maxNumberConfiguredSpatialRelations N8 = new RRC_maxNumberConfiguredSpatialRelations(1);
        public static final RRC_maxNumberConfiguredSpatialRelations N16 = new RRC_maxNumberConfiguredSpatialRelations(2);
        public static final RRC_maxNumberConfiguredSpatialRelations N32 = new RRC_maxNumberConfiguredSpatialRelations(3);
        public static final RRC_maxNumberConfiguredSpatialRelations N64 = new RRC_maxNumberConfiguredSpatialRelations(4);
        public static final RRC_maxNumberConfiguredSpatialRelations N96 = new RRC_maxNumberConfiguredSpatialRelations(5);
    
        private RRC_maxNumberConfiguredSpatialRelations(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberActiveSpatialRelations extends AsnEnumerated {
        public static final RRC_maxNumberActiveSpatialRelations N1 = new RRC_maxNumberActiveSpatialRelations(0);
        public static final RRC_maxNumberActiveSpatialRelations N2 = new RRC_maxNumberActiveSpatialRelations(1);
        public static final RRC_maxNumberActiveSpatialRelations N4 = new RRC_maxNumberActiveSpatialRelations(2);
        public static final RRC_maxNumberActiveSpatialRelations N8 = new RRC_maxNumberActiveSpatialRelations(3);
        public static final RRC_maxNumberActiveSpatialRelations N14 = new RRC_maxNumberActiveSpatialRelations(4);
    
        private RRC_maxNumberActiveSpatialRelations(long value) {
            super(value);
        }
    }

    public static class RRC_additionalActiveSpatialRelationPUCCH extends AsnEnumerated {
        public static final RRC_additionalActiveSpatialRelationPUCCH SUPPORTED = new RRC_additionalActiveSpatialRelationPUCCH(0);
    
        private RRC_additionalActiveSpatialRelationPUCCH(long value) {
            super(value);
        }
    }
}

