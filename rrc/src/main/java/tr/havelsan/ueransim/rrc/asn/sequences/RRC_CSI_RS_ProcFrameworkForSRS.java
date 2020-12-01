/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_RS_ProcFrameworkForSRS extends RRC_Sequence {

    public RRC_Integer maxNumberPeriodicSRS_AssocCSI_RS_PerBWP;
    public RRC_Integer maxNumberAperiodicSRS_AssocCSI_RS_PerBWP;
    public RRC_Integer maxNumberSP_SRS_AssocCSI_RS_PerBWP;
    public RRC_Integer simultaneousSRS_AssocCSI_RS_PerCC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberPeriodicSRS-AssocCSI-RS-PerBWP","maxNumberAperiodicSRS-AssocCSI-RS-PerBWP","maxNumberSP-SRS-AssocCSI-RS-PerBWP","simultaneousSRS-AssocCSI-RS-PerCC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberPeriodicSRS_AssocCSI_RS_PerBWP","maxNumberAperiodicSRS_AssocCSI_RS_PerBWP","maxNumberSP_SRS_AssocCSI_RS_PerBWP","simultaneousSRS_AssocCSI_RS_PerCC" };
    }

    @Override
    public String getAsnName() {
        return "CSI-RS-ProcFrameworkForSRS";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-RS-ProcFrameworkForSRS";
    }

}
