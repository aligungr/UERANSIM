/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_UAC_AccessCategory1_SelectionAssistanceInfo;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Q_QualMin;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Q_RxLevMin;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringInfoSetList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerCatList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerPLMN_List;

public class RRC_SIB1 extends AsnSequence {
    public RRC_cellSelectionInfo cellSelectionInfo; // optional
    public RRC_CellAccessRelatedInfo cellAccessRelatedInfo; // mandatory
    public RRC_ConnEstFailureControl connEstFailureControl; // optional
    public RRC_SI_SchedulingInfo si_SchedulingInfo; // optional
    public RRC_ServingCellConfigCommonSIB servingCellConfigCommon; // optional
    public RRC_ims_EmergencySupport ims_EmergencySupport; // optional
    public RRC_eCallOverIMS_Support eCallOverIMS_Support; // optional
    public RRC_UE_TimersAndConstants ue_TimersAndConstants; // optional
    public RRC_uac_BarringInfo uac_BarringInfo; // optional
    public RRC_useFullResumeID useFullResumeID; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_41 nonCriticalExtension; // optional

    public static class RRC_eCallOverIMS_Support extends AsnEnumerated {
        public static final RRC_eCallOverIMS_Support TRUE = new RRC_eCallOverIMS_Support(0);
    
        private RRC_eCallOverIMS_Support(long value) {
            super(value);
        }
    }

    public static class RRC_ims_EmergencySupport extends AsnEnumerated {
        public static final RRC_ims_EmergencySupport TRUE = new RRC_ims_EmergencySupport(0);
    
        private RRC_ims_EmergencySupport(long value) {
            super(value);
        }
    }

    public static class RRC_useFullResumeID extends AsnEnumerated {
        public static final RRC_useFullResumeID TRUE = new RRC_useFullResumeID(0);
    
        private RRC_useFullResumeID(long value) {
            super(value);
        }
    }

    public static class RRC_cellSelectionInfo extends AsnSequence {
        public RRC_Q_RxLevMin q_RxLevMin; // mandatory
        public AsnInteger q_RxLevMinOffset; // optional, VALUE(1..8)
        public RRC_Q_RxLevMin q_RxLevMinSUL; // optional
        public RRC_Q_QualMin q_QualMin; // optional
        public AsnInteger q_QualMinOffset; // optional, VALUE(1..8)
    }

    public static class RRC_uac_BarringInfo extends AsnSequence {
        public RRC_UAC_BarringPerCatList uac_BarringForCommon; // optional
        public RRC_UAC_BarringPerPLMN_List uac_BarringPerPLMN_List; // optional
        public RRC_UAC_BarringInfoSetList uac_BarringInfoSetList; // mandatory
        public RRC_uac_AccessCategory1_SelectionAssistanceInfo uac_AccessCategory1_SelectionAssistanceInfo; // optional
    
        public static class RRC_uac_AccessCategory1_SelectionAssistanceInfo extends AsnChoice {
            public RRC_UAC_AccessCategory1_SelectionAssistanceInfo plmnCommon;
            public RRC_individualPLMNList individualPLMNList; // SIZE(2..12)
        
            // SIZE(2..12)
            public static class RRC_individualPLMNList extends AsnSequenceOf<RRC_UAC_AccessCategory1_SelectionAssistanceInfo> {
            }
        }
    }

    public static class RRC_nonCriticalExtension_41 extends AsnSequence {
    }
}

