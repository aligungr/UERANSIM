/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RedirectedCarrierInfo;

public class RRC_RRCRelease_IEs extends AsnSequence {
    public RRC_RedirectedCarrierInfo redirectedCarrierInfo; // optional
    public RRC_CellReselectionPriorities cellReselectionPriorities; // optional
    public RRC_SuspendConfig suspendConfig; // optional
    public RRC_deprioritisationReq deprioritisationReq; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_RRCRelease_v1540_IEs nonCriticalExtension; // optional

    public static class RRC_deprioritisationReq extends AsnSequence {
        public RRC_deprioritisationType deprioritisationType; // mandatory
        public RRC_deprioritisationTimer deprioritisationTimer; // mandatory
    
        public static class RRC_deprioritisationTimer extends AsnEnumerated {
            public static final RRC_deprioritisationTimer MIN5 = new RRC_deprioritisationTimer(0);
            public static final RRC_deprioritisationTimer MIN10 = new RRC_deprioritisationTimer(1);
            public static final RRC_deprioritisationTimer MIN15 = new RRC_deprioritisationTimer(2);
            public static final RRC_deprioritisationTimer MIN30 = new RRC_deprioritisationTimer(3);
        
            private RRC_deprioritisationTimer(long value) {
                super(value);
            }
        }
    
        public static class RRC_deprioritisationType extends AsnEnumerated {
            public static final RRC_deprioritisationType FREQUENCY = new RRC_deprioritisationType(0);
            public static final RRC_deprioritisationType NR = new RRC_deprioritisationType(1);
        
            private RRC_deprioritisationType(long value) {
                super(value);
            }
        }
    }
}

