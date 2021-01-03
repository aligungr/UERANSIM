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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiBandInfoListEUTRA;

public class RRC_CGI_InfoEUTRA extends AsnSequence {
    public RRC_cgi_info_EPC cgi_info_EPC; // optional
    public RRC_cgi_info_5GC cgi_info_5GC; // optional, SIZE(1..12)
    public RRC_FreqBandIndicatorEUTRA freqBandIndicator; // mandatory
    public RRC_MultiBandInfoListEUTRA multiBandInfoList; // optional
    public RRC_freqBandIndicatorPriority freqBandIndicatorPriority; // optional

    public static class RRC_freqBandIndicatorPriority extends AsnEnumerated {
        public static final RRC_freqBandIndicatorPriority TRUE = new RRC_freqBandIndicatorPriority(0);
    
        private RRC_freqBandIndicatorPriority(long value) {
            super(value);
        }
    }

    // SIZE(1..12)
    public static class RRC_cgi_info_5GC extends AsnSequenceOf<RRC_CellAccessRelatedInfo_EUTRA_5GC> {
    }

    public static class RRC_cgi_info_EPC extends AsnSequence {
        public RRC_CellAccessRelatedInfo_EUTRA_EPC cgi_info_EPC_legacy; // mandatory
        public RRC_cgi_info_EPC_list cgi_info_EPC_list; // optional, SIZE(1..12)
    
        // SIZE(1..12)
        public static class RRC_cgi_info_EPC_list extends AsnSequenceOf<RRC_CellAccessRelatedInfo_EUTRA_EPC> {
        }
    }
}

