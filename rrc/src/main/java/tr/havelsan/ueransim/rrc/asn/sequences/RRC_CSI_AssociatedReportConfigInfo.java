/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ReportConfigId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_CSI_AssociatedReportConfigInfo extends AsnSequence {
    public RRC_CSI_ReportConfigId reportConfigId; // mandatory
    public RRC_resourcesForChannel resourcesForChannel; // mandatory
    public AsnInteger csi_IM_ResourcesForInterference; // optional, VALUE(1..16)
    public AsnInteger nzp_CSI_RS_ResourcesForInterference; // optional, VALUE(1..16)

    public static class RRC_resourcesForChannel extends AsnChoice {
        public RRC_nzp_CSI_RS nzp_CSI_RS;
        public AsnInteger csi_SSB_ResourceSet; // VALUE(1)
    
        public static class RRC_nzp_CSI_RS extends AsnSequence {
            public AsnInteger resourceSet; // mandatory, VALUE(1..16)
            public RRC_qcl_info qcl_info; // optional, SIZE(1..16)
        
            // SIZE(1..16)
            public static class RRC_qcl_info extends AsnSequenceOf<RRC_TCI_StateId> {
            }
        }
    }
}

