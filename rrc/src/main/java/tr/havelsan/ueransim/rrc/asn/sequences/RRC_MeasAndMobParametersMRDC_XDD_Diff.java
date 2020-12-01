/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersMRDC_XDD_Diff extends RRC_Sequence {

    public RRC_Integer sftd_MeasPSCell;
    public RRC_Integer sftd_MeasNR_Cell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sftd-MeasPSCell","sftd-MeasNR-Cell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sftd_MeasPSCell","sftd_MeasNR_Cell" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParametersMRDC-XDD-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParametersMRDC-XDD-Diff";
    }

}
