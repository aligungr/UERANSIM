/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ResultsPerCSI_RS_IndexList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ResultsPerSSB_IndexList;

public class RRC_MeasResultNR extends AsnSequence {
    public RRC_PhysCellId physCellId; // optional
    public RRC_measResult measResult; // mandatory
    public RRC_ext1_22 ext1; // optional

    public static class RRC_ext1_22 extends AsnSequence {
        public RRC_CGI_InfoNR cgi_Info; // optional
    }

    public static class RRC_measResult extends AsnSequence {
        public RRC_cellResults cellResults; // mandatory
        public RRC_rsIndexResults rsIndexResults; // optional
    
        public static class RRC_cellResults extends AsnSequence {
            public RRC_MeasQuantityResults resultsSSB_Cell; // optional
            public RRC_MeasQuantityResults resultsCSI_RS_Cell; // optional
        }
    
        public static class RRC_rsIndexResults extends AsnSequence {
            public RRC_ResultsPerSSB_IndexList resultsSSB_Indexes; // optional
            public RRC_ResultsPerCSI_RS_IndexList resultsCSI_RS_Indexes; // optional
        }
    }
}

