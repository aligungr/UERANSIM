/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_MeasResultEUTRA extends RRC_Sequence {

    public RRC_PhysCellId eutra_PhysCellId;
    public RRC_MeasQuantityResultsEUTRA measResult;
    public RRC_CGI_InfoEUTRA cgi_Info;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra-PhysCellId","measResult","cgi-Info" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra_PhysCellId","measResult","cgi_Info" };
    }

    @Override
    public String getAsnName() {
        return "MeasResultEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultEUTRA";
    }

}
