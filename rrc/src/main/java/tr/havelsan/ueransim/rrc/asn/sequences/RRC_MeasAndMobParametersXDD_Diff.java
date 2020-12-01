/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersXDD_Diff extends RRC_Sequence {

    public RRC_Integer intraAndInterF_MeasAndReport;
    public RRC_Integer eventA_MeasAndReport;
    public RRC_MeasAndMobParametersXDD_Diff__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "intraAndInterF-MeasAndReport","eventA-MeasAndReport","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "intraAndInterF_MeasAndReport","eventA_MeasAndReport","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParametersXDD-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParametersXDD-Diff";
    }

}
