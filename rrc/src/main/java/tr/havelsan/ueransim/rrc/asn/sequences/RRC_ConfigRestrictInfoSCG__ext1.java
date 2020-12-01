/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ConfigRestrictInfoSCG__ext1__selectedBandEntriesMN;

public class RRC_ConfigRestrictInfoSCG__ext1 extends RRC_Sequence {

    public RRC_ConfigRestrictInfoSCG__ext1__selectedBandEntriesMN selectedBandEntriesMN;
    public RRC_Integer pdcch_BlindDetectionSCG;
    public RRC_Integer maxNumberROHC_ContextSessionsSN;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "selectedBandEntriesMN","pdcch-BlindDetectionSCG","maxNumberROHC-ContextSessionsSN" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "selectedBandEntriesMN","pdcch_BlindDetectionSCG","maxNumberROHC_ContextSessionsSN" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
