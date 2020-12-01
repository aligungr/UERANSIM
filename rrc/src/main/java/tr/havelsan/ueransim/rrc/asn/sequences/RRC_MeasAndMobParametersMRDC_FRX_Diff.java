/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersMRDC_FRX_Diff extends RRC_Sequence {

    public RRC_Integer simultaneousRxDataSSB_DiffNumerology;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "simultaneousRxDataSSB-DiffNumerology" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "simultaneousRxDataSSB_DiffNumerology" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParametersMRDC-FRX-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParametersMRDC-FRX-Diff";
    }

}
