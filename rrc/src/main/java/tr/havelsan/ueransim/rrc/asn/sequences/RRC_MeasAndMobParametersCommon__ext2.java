/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersCommon__ext2 extends RRC_Sequence {

    public RRC_Integer independentGapConfig;
    public RRC_Integer periodicEUTRA_MeasAndReport;
    public RRC_Integer handoverFR1_FR2;
    public RRC_Integer maxNumberCSI_RS_RRM_RS_SINR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "independentGapConfig","periodicEUTRA-MeasAndReport","handoverFR1-FR2","maxNumberCSI-RS-RRM-RS-SINR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "independentGapConfig","periodicEUTRA_MeasAndReport","handoverFR1_FR2","maxNumberCSI_RS_RRM_RS_SINR" };
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
