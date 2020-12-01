/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_CG_Config_v1560_IEs__reportCGI_RequestEUTRA__requestedCellInfoEUTRA extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA eutraFrequency;
    public RRC_EUTRA_PhysCellId cellForWhichToReportCGI_EUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutraFrequency","cellForWhichToReportCGI-EUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutraFrequency","cellForWhichToReportCGI_EUTRA" };
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
