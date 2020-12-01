/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_EUTRA_ParametersXDD_Diff extends RRC_Sequence {

    public RRC_Integer rsrqMeasWidebandEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rsrqMeasWidebandEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rsrqMeasWidebandEUTRA" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-ParametersXDD-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-ParametersXDD-Diff";
    }

}
