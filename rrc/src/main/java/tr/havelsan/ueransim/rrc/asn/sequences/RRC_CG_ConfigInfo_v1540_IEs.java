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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PH_TypeListMCG;

public class RRC_CG_ConfigInfo_v1540_IEs extends AsnSequence {
    public RRC_PH_TypeListMCG ph_InfoMCG; // optional
    public RRC_measResultReportCGI measResultReportCGI; // optional
    public RRC_CG_ConfigInfo_v1560_IEs nonCriticalExtension; // optional

    public static class RRC_measResultReportCGI extends AsnSequence {
        public RRC_ARFCN_ValueNR ssbFrequency; // mandatory
        public RRC_PhysCellId cellForWhichToReportCGI; // mandatory
        public RRC_CGI_InfoNR cgi_Info; // mandatory
    }
}

