/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PH_TypeListSCG;

public class RRC_CG_Config_v1540_IEs extends AsnSequence {
    public RRC_ARFCN_ValueNR pSCellFrequency; // optional
    public RRC_reportCGI_RequestNR reportCGI_RequestNR; // optional
    public RRC_PH_TypeListSCG ph_InfoSCG; // optional
    public RRC_CG_Config_v1560_IEs nonCriticalExtension; // optional

    public static class RRC_reportCGI_RequestNR extends AsnSequence {
        public RRC_requestedCellInfo requestedCellInfo; // optional
    
        public static class RRC_requestedCellInfo extends AsnSequence {
            public RRC_ARFCN_ValueNR ssbFrequency; // mandatory
            public RRC_PhysCellId cellForWhichToReportCGI; // mandatory
        }
    }
}

