/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersMRDC_XDD_Diff_v1560 extends RRC_Sequence {

    public RRC_Integer sftd_MeasPSCell_NEDC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sftd-MeasPSCell-NEDC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sftd_MeasPSCell_NEDC" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParametersMRDC-XDD-Diff-v1560";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParametersMRDC-XDD-Diff-v1560";
    }

}
