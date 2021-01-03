/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RadioLinkMonitoringRS_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_RadioLinkMonitoringRS extends AsnSequence {
    public RRC_RadioLinkMonitoringRS_Id radioLinkMonitoringRS_Id; // mandatory
    public RRC_purpose purpose; // mandatory
    public RRC_detectionResource detectionResource; // mandatory

    public static class RRC_purpose extends AsnEnumerated {
        public static final RRC_purpose BEAMFAILURE = new RRC_purpose(0);
        public static final RRC_purpose RLF = new RRC_purpose(1);
        public static final RRC_purpose BOTH = new RRC_purpose(2);
    
        private RRC_purpose(long value) {
            super(value);
        }
    }

    public static class RRC_detectionResource extends AsnChoice {
        public RRC_SSB_Index ssb_Index;
        public RRC_NZP_CSI_RS_ResourceId csi_RS_Index;
    }
}

