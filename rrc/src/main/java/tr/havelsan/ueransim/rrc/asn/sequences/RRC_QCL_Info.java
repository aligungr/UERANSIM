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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_QCL_Info extends AsnSequence {
    public RRC_ServCellIndex cell; // optional
    public RRC_BWP_Id bwp_Id; // optional
    public RRC_referenceSignal_1 referenceSignal; // mandatory
    public RRC_qcl_Type qcl_Type; // mandatory

    public static class RRC_referenceSignal_1 extends AsnChoice {
        public RRC_NZP_CSI_RS_ResourceId csi_rs;
        public RRC_SSB_Index ssb;
    }

    public static class RRC_qcl_Type extends AsnEnumerated {
        public static final RRC_qcl_Type TYPEA = new RRC_qcl_Type(0);
        public static final RRC_qcl_Type TYPEB = new RRC_qcl_Type(1);
        public static final RRC_qcl_Type TYPEC = new RRC_qcl_Type(2);
        public static final RRC_qcl_Type TYPED = new RRC_qcl_Type(3);
    
        private RRC_qcl_Type(long value) {
            super(value);
        }
    }
}

