/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_CG_ConfigInfo_v1560_IEs__measResultReportCGI_EUTRA extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA eutraFrequency;
    public RRC_EUTRA_PhysCellId cellForWhichToReportCGI_EUTRA;
    public RRC_CGI_InfoEUTRA cgi_InfoEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutraFrequency","cellForWhichToReportCGI-EUTRA","cgi-InfoEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutraFrequency","cellForWhichToReportCGI_EUTRA","cgi_InfoEUTRA" };
    }

}
